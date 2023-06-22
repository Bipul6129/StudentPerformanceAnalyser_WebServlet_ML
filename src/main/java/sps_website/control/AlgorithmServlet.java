package sps_website.control;

import java.io.IOException;

import javax.servlet.http.*;
import sps_website.algorithm.*;
import sps_website.db.conection.logic.SendJsonMessage;

public class AlgorithmServlet extends HttpServlet{

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		try {
			gatherAndTrigger.trigger(req);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SendJsonMessage.sendJson(res, getServletInfo());
		
	}
	
}
