package sps_website.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import sps_website.db.conection.logic.CollegeLogics;
import sps_website.db.conection.logic.ReturnJsonObject;
import sps_website.model.MyCollegeModel;
import sps_website.model.UserModel;
import com.google.gson.*;
public class MyCollegeServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		List<MyCollegeModel> collegeCollec = new ArrayList<>();
//		int userId = Integer.parseInt(req.getHeader("userId"));
		UserModel user = (UserModel)req.getSession().getAttribute("user");
		
		try {
			collegeCollec = CollegeLogics.getMyCollege(user.getUserid());
			
			Gson gson = new Gson();
			String lists = gson.toJson(collegeCollec);
			
			res.setContentType(lists);
			res.getWriter().write(lists);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		UserModel user = (UserModel)req.getSession().getAttribute("user");
		String collegeName = req.getParameter("collegeName");
		String location = req.getParameter("collegeLocation");
		
		
		try {
			boolean status = CollegeLogics.insertCollege(user.getUserid(), collegeName, location);
			if(status) {
				res.setContentType("app;ication/json");
				String resData = "{\"message\":\"inserted\"}";
				PrintWriter out = res.getWriter();
				out.print(resData);
				out.flush();
			}else {
				res.setContentType("app;ication/json");
				String resData = "{\"message\":\"notinserted\"}";
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
	
	public void doDelete(HttpServletRequest req,HttpServletResponse res) throws IOException {
		JsonObject jsonData = ReturnJsonObject.returnJObject(req);
		int collegeId = jsonData.get("collegeId").getAsInt();
		
		try {
			boolean Courseexists = CollegeLogics.Courseexist(collegeId);
			if(Courseexists) {
				System.out.println("there are course");
				res.setContentType("app;ication/json");
				String resData = "{\"message\":\"courseExists\"}";
				PrintWriter out = res.getWriter();
				out.print(resData);
				out.flush();
				
			}else {
				boolean status=CollegeLogics.deleteCollege(collegeId);
				if(status) {
					res.setContentType("app;ication/json");
					String resData = "{\"message\":\"deleted\"}";
					PrintWriter out = res.getWriter();
					out.print(resData);
					out.flush();
				}else {
					res.setContentType("app;ication/json");
					String resData = "{\"message\":\"notdeleted\"}";
					PrintWriter out = res.getWriter();
					out.print(resData);
					out.flush();
				}
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
