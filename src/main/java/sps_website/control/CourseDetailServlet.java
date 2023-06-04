package sps_website.control;
import sps_website.db.conection.logic.AttendanceAnalysisLogics;
import com.google.gson.*;
import sps_website.model.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

public class CourseDetailServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		UserModel currentUser = (UserModel) session.getAttribute("user");
		int userId = currentUser.getUserid();
		int courseId = Integer.parseInt(req.getHeader("courseId"));
		int month = Integer.parseInt(req.getHeader("month"));
		int year = Integer.parseInt(req.getHeader("year"));
		System.out.println("month:"+month+"year"+year+"courseId:"+courseId);
		AttendanceModel model = new AttendanceModel();
		model = AttendanceAnalysisLogics.getTotalStudents(userId, courseId);
		model = AttendanceAnalysisLogics.getStudentAttendanceAnalysis(courseId, month, year, model);
		model = AttendanceAnalysisLogics.getAllTimeAnalysis(courseId, month, year, model);
		model = AttendanceAnalysisLogics.getTotalClassDays(courseId, model);
		if(model!=null) {
			Gson gson = new Gson();
			String json = gson.toJson(model);
			
			
			res.setContentType("application/json");
//			String resData = "{\"totalstudent\":\""+model.getTotalStudents()+"\",\"courseName\":\""+model.getCourseName()+"\"}";
			
			PrintWriter out = res.getWriter();
			out.print(json);
			
			out.flush();
		}
	}
	
}
