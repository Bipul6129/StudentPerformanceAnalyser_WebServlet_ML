package sps_website.control;
import sps_website.db.conection.logic.*;
import sps_website.model.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.*;

import com.google.gson.Gson;

public class TestBreadServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		int courseId = Integer.parseInt(req.getParameter("courseId"));
		try {
			TestAnalysisModel model = TestAnalysisLogics.getTestAnalysis(courseId);
			
			Gson gson = new Gson();
			String modelLists = gson.toJson(model);
			
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
