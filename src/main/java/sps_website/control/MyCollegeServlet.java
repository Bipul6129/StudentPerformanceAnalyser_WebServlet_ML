package sps_website.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sps_website.db.conection.logic.CollegeLogics;
import sps_website.model.MyCollegeModel;
import sps_website.model.UserModel;

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
}
