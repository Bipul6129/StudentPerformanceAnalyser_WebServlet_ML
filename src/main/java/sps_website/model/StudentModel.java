package sps_website.model;

public class StudentModel {
	private int studentId;
	private String studentName;
	private int age;
	private String ethnicity;
	private String gender;
	private String studentStatus;
	private int courseId;
	private int attendanceStatus;
	
	public int getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setAttendanceStatus(int attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEthnicity() {
		return ethnicity;
	}
	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStudentStatus() {
		return studentStatus;
	}
	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public StudentModel() {
		
	}
	
	public StudentModel(int studentId, String studentName, int age, String ethnicity, String gender,
			String studentStatus,int courseId) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.age = age;
		this.ethnicity = ethnicity;
		this.gender = gender;
		this.studentStatus = studentStatus;
		this.courseId = courseId;
	}
	
	
	
}
