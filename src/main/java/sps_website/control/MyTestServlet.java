package sps_website.control;
import javax.servlet.http.*;
import com.google.gson.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import sps_website.db.conection.logic.*;
import sps_website.model.*;

public class MyTestServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		int courseId = Integer.parseInt(req.getParameter("courseId"));
		
		List<TestModel> myTestList = new ArrayList<>();
		try {
			
			myTestList = MyTestLogics.getMyTest(courseId);
			Gson gson = new Gson();
			String myTests = gson.toJson(myTestList);
			
			res.setContentType("application/json");
			res.getWriter().write(myTests);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		TestModel test = new TestModel();
		test.setTestName(req.getParameter("testName"));
		test.setFullMarks(Integer.parseInt(req.getParameter("fullMarks")));
		test.setPassMarks(Integer.parseInt(req.getParameter("passMarks")));
		test.setCourseId(Integer.parseInt(req.getParameter("testCourse")));
		
		try {
			boolean status = MyTestLogics.insertTest(test);
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
	
	public void doDelete(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		JsonObject jsonData = ReturnJsonObject.returnJObject(req);
		int test_id = jsonData.get("test_id").getAsInt();
		int course_id = jsonData.get("course_id").getAsInt();
		
		try {
			boolean deleted = MyTestLogics.deleteTest(test_id, course_id);
			
			if(deleted) {
				SendJsonMessage.sendJson(res,"deleted");
				
			}else {
				SendJsonMessage.sendJson(res,"notdeleted");
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
		String testName = jsonData.get("testName").getAsString();
		int fullMarks = jsonData.get("fullMarks").getAsInt();
		int passMarks = jsonData.get("passMarks").getAsInt();
		int testId = jsonData. get("testId").getAsInt();
		int courseId = jsonData.get("courseId").getAsInt();
		
		TestModel model = new TestModel(testId,testName,fullMarks,passMarks,courseId);
		
		try {
			boolean status = MyTestLogics.editTest(model);
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
