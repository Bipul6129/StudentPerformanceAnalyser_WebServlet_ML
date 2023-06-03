package sps_website.model;

public class AttendanceModel {
	private String courseName;
	private int totalStudents;
	private int highestPresentMonth;
	private int lowestPresentMonth;
	
	public int getLowestPresentMonth() {
		return lowestPresentMonth;
	}
	public void setLowestPresentMonth(int lowestPresentMonth) {
		this.lowestPresentMonth = lowestPresentMonth;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getTotalStudents() {
		return totalStudents;
	}
	public void setTotalStudents(int totalStudents) {
		this.totalStudents = totalStudents;
	}
	public AttendanceModel(String courseName, int totalStudents) {
		super();
		this.courseName = courseName;
		this.totalStudents = totalStudents;
	}
	
	public int getHighestPresentMonth() {
		return highestPresentMonth;
	}
	public void setHighestPresentMonth(int highestPresentMonth) {
		this.highestPresentMonth = highestPresentMonth;
	}
	public AttendanceModel() {
		
	}
	
}
