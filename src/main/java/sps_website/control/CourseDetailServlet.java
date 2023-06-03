package sps_website.control;
import sps_website.db.conection.logic.AttendanceAnalysisLogics;
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
		
		
		AttendanceModel model = new AttendanceModel();
		model = AttendanceAnalysisLogics.getTotalStudents(userId, courseId);

		
		if(model!=null) {
			res.setContentType("application/json");
			String resData = "{\"totalstudent\":\""+model.getTotalStudents()+"\",\"courseName\":\""+model.getCourseName()+"\"}";
			
			PrintWriter out = res.getWriter();
			out.print(resData);
			
			out.flush();
		}
	}
	
}
