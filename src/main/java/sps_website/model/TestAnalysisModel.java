package sps_website.model;

public class TestAnalysisModel {
	private String HighStudentName;
	private String LowStudentName;
	private int highMarks;
	private int lowMarks;
	private int fullMarks;
	public String getHighStudentName() {
		return HighStudentName;
	}
	public void setHighStudentName(String highStudentName) {
		HighStudentName = highStudentName;
	}
	public String getLowStudentName() {
		return LowStudentName;
	}
	public void setLowStudentName(String lowStudentName) {
		LowStudentName = lowStudentName;
	}
	public int getHighMarks() {
		return highMarks;
	}
	public void setHighMarks(int highMarks) {
		this.highMarks = highMarks;
	}
	public int getLowMarks() {
		return lowMarks;
	}
	public void setLowMarks(int lowMarks) {
		this.lowMarks = lowMarks;
	}
	public int getFullMarks() {
		return fullMarks;
	}
	public void setFullMarks(int fullMarks) {
		this.fullMarks = fullMarks;
	}
	public TestAnalysisModel(String highStudentName, String lowStudentName, int highMarks, int lowMarks,
			int fullMarks) {
		super();
		HighStudentName = highStudentName;
		LowStudentName = lowStudentName;
		this.highMarks = highMarks;
		this.lowMarks = lowMarks;
		this.fullMarks = fullMarks;
	}
	
	
	
}
