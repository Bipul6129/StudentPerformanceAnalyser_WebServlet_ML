package sps_website.control;
import sps_website.db.conection.logic.*;
import javax.servlet.http.*;
import sps_website.model.*;
import com.google.gson.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
public class getMyCollegeServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		List<MyCollegeModel> collegeCollec = new ArrayList<>();
		int userId = Integer.parseInt(req.getHeader("userId"));
		try {
			collegeCollec = getMyCollegeLogics.getMyCollege(userId);
			
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
