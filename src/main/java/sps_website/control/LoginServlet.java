package sps_website.control;
import sps_website.model.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.*;

import sps_website.db.conection.logic.LoginLogic;

public class LoginServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		try {
			UserModel currentUser = LoginLogic.loginCheck(username, password);
			if(currentUser!=null) {
				if(currentUser.getIsAdmin()==0) {
					System.out.println("loggedin");
					res.setContentType("application/json");
					String responseData = "{\"message\": \"correct\"}";
					PrintWriter out = res.getWriter();
					out.print(responseData);
					out.flush();
					HttpSession session = req.getSession();
					session.setAttribute("user",currentUser);
				}
				
			}else {
				System.out.println("notLoggedin");
				res.setContentType("application/json");
				String responseData = "{\"message\":\"incorrect\"}";
				PrintWriter out = res.getWriter();
				out.print(responseData);
				out.flush();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
