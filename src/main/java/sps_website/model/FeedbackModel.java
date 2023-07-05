package sps_website.model;

public class FeedbackModel {
	private String date;
	private String subject;
	private String message;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public FeedbackModel(String date, String subject, String message) {
		super();
		this.date = date;
		this.subject = subject;
		this.message = message;
	}
	
	
	
}
