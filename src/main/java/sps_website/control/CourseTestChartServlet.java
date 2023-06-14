package sps_website.control;
import sps_website.db.conection.logic.*;
import sps_website.model.TestChartModel;
import com.google.gson.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.http.*;

public class CourseTestChartServlet extends HttpServlet{
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		int courseId = Integer.parseInt(req.getParameter("courseId"));
		try {
			List<TestChartModel> models = TestAnalysisLogics.getChartData(courseId);
			
			Gson gson = new Gson();
			String modelLists = gson.toJson(models);
			
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
