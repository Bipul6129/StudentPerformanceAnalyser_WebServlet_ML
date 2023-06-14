package sps_website.model;

public class MarksSheetModel {
	private int studentId;
	private int marksObtained;
	private int testId;
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public int getMarksObtained() {
		return marksObtained;
	}
	public void setMarksObtained(int marksObtained) {
		this.marksObtained = marksObtained;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public MarksSheetModel(int studentId, int marksObtained, int testId) {
		super();
		this.studentId = studentId;
		this.marksObtained = marksObtained;
		this.testId = testId;
	}
	public MarksSheetModel() {
		// TODO Auto-generated constructor stub
	}
	
	
	
}
