package sps_website.control;

import java.io.IOException;
import sps_website.model.*;
import javax.servlet.http.*;
import sps_website.algorithm.*;
import sps_website.db.conection.logic.SendJsonMessage;

public class AlgorithmServlet extends HttpServlet{

	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		String outcome="";
		String sendMsg="";
		try {
			PredictModel model = new PredictModel();
			model.setEthnicity(req.getParameter("ethnic"));
			model.setAge(req.getParameter("age"));
			model.setGender(req.getParameter("gender"));
			model.setStatus(req.getParameter("status"));
			outcome=gatherAndTrigger.trigger(req,model);
			System.out.println("The outcome is "+outcome);
			SendJsonMessage.sendJson(res,outcome);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
