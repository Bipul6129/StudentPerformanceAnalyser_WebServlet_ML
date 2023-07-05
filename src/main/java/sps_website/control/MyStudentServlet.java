package sps_website.control;
import sps_website.db.conection.logic.*;
import sps_website.model.*;
import com.google.gson.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.http.*;

public class MyStudentServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String StudentName = req.getParameter("studentName");
		int courseId = Integer.parseInt(req.getParameter("courseSelect"));
		int age = Integer.parseInt(req.getParameter("studentAge"));
		String ethnicity = req.getParameter("studentEthnicity");
		String gender = req.getParameter("studentGender");
		String studentStatus = req.getParameter("studentStatus");
		String studentEmail = req.getParameter("studentEmail");
		try {
			boolean status = StudentLogics.insertStudent(StudentName,studentEmail, courseId, age, ethnicity, gender, studentStatus);
			if(status) {
				res.setContentType("application/json");
				String resData = "{\"message\":\"inserted\"}";
				PrintWriter out = res.getWriter();
				out.print(resData);
				out.flush();
			}else {
				res.setContentType("application/json");
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
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		int courseId = Integer.parseInt(req.getParameter("courseId"));
		
		List<StudentModel> myStudent = new ArrayList<>();
		try {
			
			myStudent = StudentLogics.getMyStudents(courseId);
			
			Gson gson = new Gson();
			String myStudents = gson.toJson(myStudent);
			
			res.setContentType("application/json");
			res.getWriter().write(myStudents);
			
			
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
		int studentId = jsonData.get("studentId").getAsInt();
		
		HttpSession session = req.getSession();
		UserModel user = (UserModel) session.getAttribute("user");
		int userId = user.getUserid();
		
		try {
			boolean checkStudent = StudentLogics.isMyStudent(userId, studentId);
			if(checkStudent) {
				System.out.println("student is yours");
				boolean deleted = StudentLogics.removeStudent(studentId);
				
				if(deleted) {
					SendJsonMessage.sendJson(res,"deleted");
				}else {
					SendJsonMessage.sendJson(res,"notdeleted");
				}
			}else{
				System.out.println("the student is not yours");
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
		String studentName = jsonData.get("studentName").getAsString();
		int age = jsonData.get("age").getAsInt();
		int courseId = jsonData.get("course").getAsInt();
		String ethni = jsonData.get("ethni").getAsString();
		String gender = jsonData.get("gender").getAsString();
		String status = jsonData.get("status").getAsString();
		String email = jsonData.get("studentEmail").getAsString();
		
		try {
			boolean result=StudentLogics.updateStudent(studentId, studentName, age, ethni, gender, courseId, status,email);
			if(result) {
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
