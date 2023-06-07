package sps_website.control;
import sps_website.db.conection.logic.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.*;

public class MyStudentServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String StudentName = req.getParameter("studentName");
		int courseId = Integer.parseInt(req.getParameter("courseSelect"));
		int age = Integer.parseInt(req.getParameter("studentAge"));
		String ethnicity = req.getParameter("studentEthnicity");
		String gender = req.getParameter("studentGender");
		String studentStatus = req.getParameter("studentStatus");
		
		try {
			boolean status = StudentLogics.insertStudent(StudentName, courseId, age, ethnicity, gender, studentStatus);
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
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
