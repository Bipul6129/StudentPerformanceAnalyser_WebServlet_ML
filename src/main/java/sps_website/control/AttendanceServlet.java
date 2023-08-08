package sps_website.control;
import sps_website.db.conection.logic.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import com.google.gson.*;
import javax.servlet.http.*;
import sps_website.model.*;
public class AttendanceServlet extends HttpServlet{

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String date = req.getParameter("date");
		int courseId=0;
		if(req.getParameter("courseId")!=null) {
			courseId = Integer.parseInt(req.getParameter("courseId"));
		}
		
		try {
			List<StudentModel> model = AttendanceLogics.getAttendanceofDay(date, courseId);
			AttendanceLogics.assignStudent(courseId, date);
			Gson gson = new Gson();
			String modelLists = gson.toJson(model);
			res.setContentType("application/json");
			res.getWriter().write(modelLists);
			
			
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
		
		int courseId=jsonData.get("courseId").getAsInt();
		int studentId = jsonData.get("studentId").getAsInt();
		int attendanceStatus = jsonData.get("attendanceStatus").getAsInt();
		String date = jsonData.get("date").getAsString();
		
		try {
			boolean status = AttendanceLogics.insertAttendance(studentId, date, attendanceStatus, courseId);
			if(status) {
				SendJsonMessage.sendJson(res,"success");
			}else {
				SendJsonMessage.sendJson(res,"failed");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(courseId+"we got");
	}
	
	
}
