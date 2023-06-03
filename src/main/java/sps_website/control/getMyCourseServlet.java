package sps_website.control;
import sps_website.model.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.*;
import sps_website.db.conection.logic.*;
import com.google.gson.*;

public class getMyCourseServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		int userId = Integer.parseInt(req.getHeader("userId"));
		
		List<CourseModel> myCourseList = new ArrayList<>();
		myCourseList = GetMyCourseLogic.getMyCourse(userId);
		
		Gson gson = new Gson();
		String myCourses = gson.toJson(myCourseList);
		
		res.setContentType("application/json");
		res.getWriter().write(myCourses);
		
	}
	
}
