package sps_website.control;

import java.io.IOException;

import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("user")!=null) {
			session.invalidate();
			res.sendRedirect("LoginPage.jsp");
		}
	}
}
