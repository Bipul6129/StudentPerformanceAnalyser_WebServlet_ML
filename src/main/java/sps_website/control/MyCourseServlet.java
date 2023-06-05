package sps_website.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.*;

import com.google.gson.Gson;

import sps_website.db.conection.logic.CourseLogics;
import sps_website.model.CourseModel;
import sps_website.model.UserModel;

public class MyCourseServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
//		int userId = Integer.parseInt(req.getHeader("userId"));
		
		UserModel user = (UserModel) req.getSession().getAttribute("user");
		int userId = user.getUserid();
		
		List<CourseModel> myCourseList = new ArrayList<>();
		myCourseList = CourseLogics.getMyCourse(userId);
		
		Gson gson = new Gson();
		String myCourses = gson.toJson(myCourseList);
		
		res.setContentType("application/json");
		res.getWriter().write(myCourses);
		
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		String courseName = req.getParameter("courseName");
		
		String faculty = req.getParameter("courseFaculty");
		String semester = req.getParameter("courseSemester");
		int courseCollegeId = Integer.parseInt(req.getParameter("collegeDropDown"));
		UserModel user = (UserModel)session.getAttribute("user");
		int userId= user.getUserid();
		boolean status = CourseLogics.insertCourse(courseName,faculty,semester,courseCollegeId,userId);
		
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
