package sps_website.model;

public class AttendanceModel {
	private String courseName;
	private int totalStudents;
	
	
	private String highPresentName="";
	private String lowPresentName;
	private int highPresentCount;
	private int lowPresentCount;
	private int highPresentId;
	private int lowPresentId;
	
	private String highPresetnIdList;
	private String lowPresentIdList;




	private String[] HighPresentArray;
	
	private String AllTimeHighName;
	private String AllTimeLowName;
	private int allTimeHighCount;
	private int allTimeLowCount;
	private int allTimeHighId;
	private int allTimeLowId;
	private int TotalClassDays;
	
	private String allTimeHighIdList;
	private String allTimeLowIdList;
	
	
	public String getAllTimeHighIdList() {
		return allTimeHighIdList;
	}

	public void setAllTimeHighIdList(String allTimeHighIdList) {
		this.allTimeHighIdList = allTimeHighIdList;
	}

	public String getAllTimeLowIdList() {
		return allTimeLowIdList;
	}

	public void setAllTimeLowIdList(String allTimeLowIdList) {
		this.allTimeLowIdList = allTimeLowIdList;
	}

	public String getHighPresetnIdList() {
		return highPresetnIdList;
	}

	public void setHighPresetnIdList(String highPresetnIdList) {
		this.highPresetnIdList = highPresetnIdList;
	}

	public String getLowPresentIdList() {
		return lowPresentIdList;
	}

	public void setLowPresentIdList(String lowPresentIdList) {
		this.lowPresentIdList = lowPresentIdList;
	}

	public String[] getHighPresentArray() {
		return HighPresentArray;
	}

	public void setHighPresentArray(String[] highPresentArray) {
		HighPresentArray = highPresentArray;
	}
	
	public int getTotalClassDays() {
		return TotalClassDays;
	}

	public void setTotalClassDays(int totalClassDays) {
		TotalClassDays = totalClassDays;
	}

	public AttendanceModel(int allTimeHighCount, int allTimeLowCount,
			int allTimeHighId, int allTimeLowId,String allTimeHighName, String allTimeLowName ) {
		super();
		AllTimeHighName = allTimeHighName;
		AllTimeLowName = allTimeLowName;
		this.allTimeHighCount = allTimeHighCount;
		this.allTimeLowCount = allTimeLowCount;
		this.allTimeHighId = allTimeHighId;
		this.allTimeLowId = allTimeLowId;
	}

	public String getAllTimeHighName() {
		return AllTimeHighName;
	}

	public void setAllTimeHighName(String allTimeHighName) {
		AllTimeHighName = allTimeHighName;
	}

	public String getAllTimeLowName() {
		return AllTimeLowName;
	}

	public void setAllTimeLowName(String allTimeLowName) {
		AllTimeLowName = allTimeLowName;
	}

	public int getAllTimeHighCount() {
		return allTimeHighCount;
	}

	public void setAllTimeHighCount(int allTimeHighCount) {
		this.allTimeHighCount = allTimeHighCount;
	}

	public int getAllTimeLowCount() {
		return allTimeLowCount;
	}

	public void setAllTimeLowCount(int allTimeLowCount) {
		this.allTimeLowCount = allTimeLowCount;
	}

	public int getAllTimeHighId() {
		return allTimeHighId;
	}

	public void setAllTimeHighId(int allTimeHighId) {
		this.allTimeHighId = allTimeHighId;
	}

	public int getAllTimeLowId() {
		return allTimeLowId;
	}

	public void setAllTimeLowId(int allTimeLowId) {
		this.allTimeLowId = allTimeLowId;
	}




	
	
	public AttendanceModel(String highPresentName, String lowPresentName, int highPresentCount, int lowPresentCount,
			int highPresentId, int lowPresentId) {
		super();
		this.highPresentName = highPresentName;
		this.lowPresentName = lowPresentName;
		this.highPresentCount = highPresentCount;
		this.lowPresentCount = lowPresentCount;
		this.highPresentId = highPresentId;
		this.lowPresentId = lowPresentId;
	}
	
	public String getHighPresentName() {
		return highPresentName;
	}
	public void setHighPresentName(String highPresentName) {
		this.highPresentName = highPresentName;
	}
	public String getLowPresentName() {
		return lowPresentName;
	}
	public void setLowPresentName(String lowPresentName) {
		this.lowPresentName = lowPresentName;
	}
	public int getHighPresentCount() {
		return highPresentCount;
	}
	public void setHighPresentCount(int highPresentCount) {
		this.highPresentCount = highPresentCount;
	}
	public int getLowPresentCount() {
		return lowPresentCount;
	}
	public void setLowPresentCount(int lowPresentCount) {
		this.lowPresentCount = lowPresentCount;
	}
	public int getHighPresentId() {
		return highPresentId;
	}
	public void setHighPresentId(int highPresentId) {
		this.highPresentId = highPresentId;
	}
	public int getLowPresentId() {
		return lowPresentId;
	}
	public void setLowPresentId(int lowPresentId) {
		this.lowPresentId = lowPresentId;
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
	
	
	
	
	public AttendanceModel() {
		
	}
	
}
