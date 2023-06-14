package sps_website.model;

public class StudentTestChartModel {
	private String testName;
	private float point;
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public float getPoint() {
		return point;
	}
	public void setPoint(float point) {
		this.point = point;
	}
	public StudentTestChartModel(String testName, float point) {
		super();
		this.testName = testName;
		this.point = point;
	}
	
	public StudentTestChartModel() {
		
	}
}
