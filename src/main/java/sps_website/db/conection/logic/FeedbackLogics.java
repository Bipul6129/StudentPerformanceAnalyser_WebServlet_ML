package sps_website.db.conection.logic;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.*;
import java.util.Properties;
import sps_website.db.*;
import sps_website.db.conection.EstablishConnection;
import sps_website.model.FeedbackModel;

import java.util.*;

public class FeedbackLogics {

	public static boolean sendMail(String receiverEmail,String subject,String content) {
		boolean mailSent=false;
		final String senderEmail = "bca190619_bipul@achsnepal.edu.np";
        final String password = "wzmpggjkcwossdak";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable","true");
        props.put("mail.smtp.starttls.enable", "true");
        
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });
        
        try {
            // Create a new message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiverEmail));
            message.setSubject(subject);
            message.setText(content);
            	
            // Send the message
            Transport.send(message);
            mailSent=true;
            // Success message
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            // Error message
            System.out.println("Failed to send email. Error: " + e.getMessage());
        }
		return mailSent;
	}
	
	public static boolean storeFeedback(String date,String subject,String message,int studentId) throws ClassNotFoundException, SQLException {
		boolean status = false;
		Connection con = EstablishConnection.getConnection();
		String query = "insert into feedback(feedback_date,feedback_subject,feedback_msg,student_id) values(?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(query);
		
		pst.setString(1,date);
		pst.setString(2, subject);
		pst.setString(3,message);
		pst.setInt(4, studentId);
		
		int rowAffected = pst.executeUpdate();
		
		if(rowAffected>0) {
			status=true;
		}

		return status;
	}
	
	public static List<FeedbackModel> getfeedbacks(int studentId) throws SQLException, ClassNotFoundException{
		List<FeedbackModel> model = new ArrayList<>();
		Connection con = EstablishConnection.getConnection();
		String query="select * from feedback where student_id=? order by feedback_date desc";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, studentId);
		
		ResultSet set = pst.executeQuery();
		
		while(set.next()) {
			FeedbackModel feedback = new FeedbackModel(set.getString("feedback_date"),set.getString("feedback_subject"),set.getString("feedback_msg"));
			model.add(feedback);
		}
		
		return model;
		
	}
	
}
