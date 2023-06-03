package sps_website.model;

public class CourseModel {
	private int courseId;
	private String courseName;
	private String courseFaculty;
	private String courseSemester;
	private String courseCollege;
	private int courseStatus;
	private int courseCollegeId;
	public int getCourseCollegeId() {
		return courseCollegeId;
	}
	public void setCourseCollegeId(int courseCollegeId) {
		this.courseCollegeId = courseCollegeId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseFaculty() {
		return courseFaculty;
	}
	public void setCourseFaculty(String courseFaculty) {
		this.courseFaculty = courseFaculty;
	}
	public String getCourseSemester() {
		return courseSemester;
	}
	public void setCourseSemester(String courseSemester) {
		this.courseSemester = courseSemester;
	}
	public String getCourseCollege() {
		return courseCollege;
	}
	public void setCourseCollegeId(String courseCollege) {
		this.courseCollege = courseCollege;
	}
	public int getCourseStatus() {
		return courseStatus;
	}
	public void setCourseStatus(int courseStatus) {
		this.courseStatus = courseStatus;
	}
	public CourseModel(int courseId, String courseName, String courseFaculty, String courseSemester,
			String courseCollege, int courseStatus,int courseCollegeId) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseFaculty = courseFaculty;
		this.courseSemester = courseSemester;
		this.courseCollege = courseCollege;
		this.courseStatus = courseStatus;
		this.courseCollegeId = courseCollegeId;
	}
	
}
