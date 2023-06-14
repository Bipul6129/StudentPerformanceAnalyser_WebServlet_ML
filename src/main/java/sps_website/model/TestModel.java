package sps_website.model;

public class TestModel {
	private int testId;
	private String testName;
	private int fullMarks;
	private int passMarks;
	private int courseId;
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public int getFullMarks() {
		return fullMarks;
	}
	public void setFullMarks(int fullMarks) {
		this.fullMarks = fullMarks;
	}
	public int getPassMarks() {
		return passMarks;
	}
	public void setPassMarks(int passMarks) {
		this.passMarks = passMarks;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public TestModel(int testId, String testName, int fullMarks, int passMarks, int courseId) {
		super();
		this.testId = testId;
		this.testName = testName;
		this.fullMarks = fullMarks;
		this.passMarks = passMarks;
		this.courseId = courseId;
	}
	public TestModel() {
		// TODO Auto-generated constructor stub
	}
	
	
}
