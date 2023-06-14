package sps_website.model;

public class TestChartModel {
	private String testName;
	private int passNumber;
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public int getPassNumber() {
		return passNumber;
	}
	
	public void setPassNumber(int passNumber) {
		this.passNumber = passNumber;
	}
	
	public TestChartModel(String testName, int passNumber) {
		super();
		this.testName = testName;
		this.passNumber = passNumber;
	}
}
