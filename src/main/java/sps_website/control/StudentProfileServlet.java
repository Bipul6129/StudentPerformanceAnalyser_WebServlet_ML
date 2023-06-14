package sps_website.control;
import java.io.IOException;
import java.util.*;
import java.sql.SQLException;

import javax.servlet.http.*;
import sps_website.db.conection.logic.*;
import sps_website.model.*;
import com.google.gson.*;

public class StudentProfileServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		int studentId = Integer.parseInt(req.getParameter("studentId"));
		StudentModel student = new StudentModel();
		
		try {
			student = StudentLogics.getParticularStudent(studentId, student);
			
			
			Gson gson = new Gson();
			String modelLists = gson.toJson(student);
			
			
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
	
}
