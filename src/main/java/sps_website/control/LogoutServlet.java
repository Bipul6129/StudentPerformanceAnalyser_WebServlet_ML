package sps_website.control;
import sps_website.algorithm.*;
import java.io.IOException;

import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet{
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("user")!=null) {
			if(session.getAttribute("rootNode")!=null) {
				DecisionNode rootNode = (DecisionNode) session.getAttribute("rootNode");
				rootNode.clearChild();
			}
			
			session.invalidate();
			res.sendRedirect("LoginPage.jsp");
		}else {
			res.sendRedirect("LoginPage.jsp");
		}
	}
}
