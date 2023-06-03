package sps_website.model;

public class AttendanceChartModel {
	private int day;
	private int totalPresent;
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getTotalPresent() {
		return totalPresent;
	}
	public void setTotalPresent(int totalPresent) {
		this.totalPresent = totalPresent;
	}
	public AttendanceChartModel(int day, int totalPresent) {
		super();
		this.day = day;
		this.totalPresent = totalPresent;
	}
	
	public AttendanceChartModel(){
		
	}
	
	
}
