package sps_website.control;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import sps_website.model.*;
import java.util.*;
import sps_website.db.conection.logic.*;
import com.google.gson.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/webpages/portalServlet")
public class StudentPortalLoginServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String email=req.getParameter("email");
		System.out.println(email);
		List<StudentPortalModel> studentSubjects = new ArrayList<>();
		
		studentSubjects = StudentLoginLogic.checkLogin(email);
		
		Gson gson = new Gson();
		String subjects = gson.toJson(studentSubjects);
		
		res.setContentType("application/json");
		res.getWriter().write(subjects);
	}
	
}
