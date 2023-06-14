package sps_website.control;
import sps_website.db.conection.logic.*;
import javax.servlet.http.*;
import sps_website.model.*;
import com.google.gson.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
public class MarksAllocationServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		int testId = Integer.parseInt(req.getParameter("testId"));
		List<MarksSheetModel> MarksList = new ArrayList<>();
		
		try {
			MarksList = MarksAllocation.getMarks(testId);
			Gson gson = new Gson();
			String myMarks = gson.toJson(MarksList);
			res.setContentType("application/json");
			res.getWriter().write(myMarks);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		JsonObject jsonData = ReturnJsonObject.returnJObject(req);
		int studentId = jsonData.get("studentId").getAsInt();
		int marksObtained = jsonData.get("marksObtained").getAsInt();
		int testId = jsonData.get("testId").getAsInt();
		
		MarksSheetModel model = new MarksSheetModel(studentId,marksObtained,testId);
		
		try {
			boolean status = MarksAllocation.insertMarks(model);
			if(status) {
				SendJsonMessage.sendJson(res,"inserted");
			}else {
				SendJsonMessage.sendJson(res,"notinserted");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void doPut(HttpServletRequest req,HttpServletResponse res) throws IOException {
		JsonObject jsonData = ReturnJsonObject.returnJObject(req);
		int studentId = jsonData.get("studentId").getAsInt();
		int marksObtained = jsonData.get("marksObtained").getAsInt();
		int testId = jsonData.get("testId").getAsInt();
		try {
			boolean status = MarksAllocation.updateMarks(studentId, marksObtained, testId);
			if(status) {
				SendJsonMessage.sendJson(res,"updated");
			}else {
				SendJsonMessage.sendJson(res,"notupdated");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	
}
