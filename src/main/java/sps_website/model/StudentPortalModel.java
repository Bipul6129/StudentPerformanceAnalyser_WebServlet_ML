package sps_website.model;

public class StudentPortalModel {
	private int course_id;
	private int student_id;
	private String courseName;
	
	
	public StudentPortalModel(int course_id, int student_id, String courseName) {
		super();
		this.course_id = course_id;
		this.student_id = student_id;
		this.courseName = courseName;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
}
