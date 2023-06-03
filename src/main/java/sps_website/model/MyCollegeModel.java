package sps_website.model;

public class MyCollegeModel {
	private int CollegeId;
	private String CollegeName;
	private String location;
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getCollegeId() {
		return CollegeId;
	}
	public void setCollegeId(int collegeId) {
		CollegeId = collegeId;
	}
	public String getCollegeName() {
		return CollegeName;
	}
	public void setCollegeName(String collegeName) {
		CollegeName = collegeName;
	}
	public MyCollegeModel(int collegeId, String collegeName,String location) {
		super();
		CollegeId = collegeId;
		CollegeName = collegeName;
		this.location = location;
	}
	
	
}
