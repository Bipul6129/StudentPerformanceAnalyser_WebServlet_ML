package sps_website.control;
import sps_website.db.conection.logic.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import sps_website.model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.Properties;
import com.google.gson.*;

public class FeedbackServlet extends HttpServlet{
	
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		String email = req.getParameter("feedback_email");
		String f_subject = req.getParameter("feedback_subject");
		String f_msg = req.getParameter("feedback_msg");
		String date = req.getParameter("today_date");
		int studentId = Integer.parseInt(req.getParameter("student_id"));
		
		System.out.println(email+" "+f_subject+" "+f_msg+" "+date+" "+studentId);
		
		boolean mailSent=FeedbackLogics.sendMail(email, f_subject, f_msg);
		try {
			boolean storeFeedback=FeedbackLogics.storeFeedback(date, f_subject, f_msg, studentId);
			
			if(storeFeedback) {
				if(mailSent) {
					SendJsonMessage.sendJson(res,"success");
				}else {
					SendJsonMessage.sendJson(res,"failed");
				}
			}else {
				SendJsonMessage.sendJson(res,"failed");
			}
			
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException {
		
		int studentId=Integer.parseInt(req.getParameter("student_id"));
		List<FeedbackModel> feedbacks = new ArrayList<>();
		
		try {
			feedbacks=FeedbackLogics.getfeedbacks(studentId);
			Gson gson = new Gson();
			String myfeedback = gson.toJson(feedbacks);
			
			res.setContentType("application/json");
			res.getWriter().write(myfeedback);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
