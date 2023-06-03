package sps_website.control;
import sps_website.model.*;
import sps_website.db.conection.logic.*;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.*;
import com.google.gson.*;


public class CourseAttendanceChartServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		int month = Integer.parseInt(req.getHeader("month"));
		int year = Integer.parseInt(req.getHeader("year"));
		int courseId = Integer.parseInt(req.getHeader("courseId"));
		
		System.out.println("month"+month+" year"+year+" courseId"+courseId);
		
		List<AttendanceChartModel> modelCollection = AttendanceAnalysisLogics.getAttendanceChartData(month, year, courseId);
		
		Gson gson = new Gson();
		String modelLists = gson.toJson(modelCollection);
		
		res.setContentType("application/json");
		res.getWriter().write(modelLists);
	}
}
