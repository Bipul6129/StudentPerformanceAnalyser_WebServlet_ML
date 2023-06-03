package sps_website.control;
import sps_website.control.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;
import sps_website.db.conection.logic.*;
import sps_website.model.*;

public class InsertCourseServlet extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		String courseName = req.getParameter("courseName");
		
		String faculty = req.getParameter("courseFaculty");
		String semester = req.getParameter("courseSemester");
		int courseCollegeId = Integer.parseInt(req.getParameter("collegeDropDown"));
		UserModel user = (UserModel)session.getAttribute("user");
		int userId= user.getUserid();
		boolean status = InsertCourseLogic.insertCourse(courseName,faculty,semester,courseCollegeId,userId);
		
		if(status) {
			res.setContentType("app;ication/json");
			String resData = "{\"message\":\"inserted\"}";
			PrintWriter out = res.getWriter();
			out.print(resData);
			out.flush();
			
		}else {
			res.setContentType("app;ication/json");
			String resData = "{\"message\":\"not inserted\"}";
			PrintWriter out = res.getWriter();
			out.print(resData);
			out.flush();
		}
		
		
	}
}
