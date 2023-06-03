package sps_website.model;

public class UserModel {
	private String username;
	private int userid;
	private int isAdmin;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public UserModel(String username,int userid,int isAdmin) {
		this.username=username;
		this.userid=userid;
		this.isAdmin = isAdmin;
	}
}
