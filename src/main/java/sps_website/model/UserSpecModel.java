package sps_website.model;

public class UserSpecModel {
	private String username;
	private String email;
	private int numOfSubjects;
	private int isBlocked;
	private int userId;

	public UserSpecModel(String username, String email, int numOfSubjects, int isBlocked,int userId) {
		super();
		this.username = username;
		this.email = email;
		this.numOfSubjects = numOfSubjects;
		this.isBlocked = isBlocked;
		this.userId=userId;
	
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNumOfSubjects() {
		return numOfSubjects;
	}
	public void setNumOfSubjects(int numOfSubjects) {
		this.numOfSubjects = numOfSubjects;
	}
	public int getIsBlocked() {
		return isBlocked;
	}
	public void setIsBlocked(int isBlocked) {
		this.isBlocked = isBlocked;
	}
	
}
