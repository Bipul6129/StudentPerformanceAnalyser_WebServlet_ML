package sps_website.control;
import javax.servlet.http.*;
import sps_website.db.conection.logic.*;
import sps_website.model.*;
import com.google.gson.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
public class StudentTestChartServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		int studentId = Integer.parseInt(req.getParameter("studentId"));
		List<StudentTestChartModel> model = new ArrayList<>();
		
		try {
			model = TestAnalysisLogics.getStudentTestChart(studentId);
			
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
