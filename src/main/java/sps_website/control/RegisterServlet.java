package sps_website.control;
import sps_website.db.conection.logic.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.*;

public class RegisterServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String email = req.getParameter("email");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		
		boolean status = RegisterLogic.RegisterUser(email, username, password);
		if(status) {
			res.setContentType("application/json");
			String responseData = "{\"message\": \"registered\"}";
			PrintWriter out = res.getWriter();
			out.print(responseData);
			out.flush();
		}else {
			res.setContentType("application/json");
			String responseData = "{\"message\": \"notregistered\"}";
			PrintWriter out = res.getWriter();
			out.print(responseData);
			out.flush();
			
		}
		
		
	}
}
