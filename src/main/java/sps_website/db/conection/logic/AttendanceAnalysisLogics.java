package sps_website.db.conection.logic;
import sps_website.model.*;
import java.sql.*;

import java.util.*;
import java.util.Date;

import sps_website.db.conection.*;
public class AttendanceAnalysisLogics {
	
	public static AttendanceModel getTotalStudents(int userId,int courseId) {
		AttendanceModel totalModel = new AttendanceModel();
		try {
			Connection con = EstablishConnection.getConnection();
			String query = "select count(student.student_id) as total_students,course.courseName from course left join student on course.course_id=student.course_id where course.course_id=? and course.user_id=?;";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, courseId);
			pst.setInt(2, userId);
			ResultSet set = pst.executeQuery();
			while(set.next()) {
				int totalStudent = set.getInt("total_students");
				String courseName = set.getString("courseName");
				totalModel.setTotalStudents(totalStudent);
				totalModel.setCourseName(courseName);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalModel;
		
	}
	
	public static AttendanceModel getHighestAttendance(int Month,int Year,int courseId,AttendanceModel model) {
		AttendanceModel highAttendance = model;
		try {
			Connection con = EstablishConnection.getConnection();
			String query = "select count(distinct attendance_date) as highest from attendance where month(attendance_date)=? and year(attendance_date)=? and status=1 and course_id=?;";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1,Month);
			pst.setInt(2,Year);
			pst.setInt(3, courseId);
			ResultSet set =pst.executeQuery();
			while(set.next()) {
				model.setHighestPresentMonth(set.getInt("highest"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return highAttendance;
	}
	
	public static AttendanceModel getLowestAttendance(int Month,int Year,int courseId,AttendanceModel model) {
		AttendanceModel lowAttendance = model;
		try {
			Connection con = EstablishConnection.getConnection();
			String query = "select count(distinct attendance_date) as lowest from attendance where month(attendance_date)=? and year(attendance_date)=? and status=1 and course_id=? group by attendance_date order by lowest asc limit 1";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1,Month);
			pst.setInt(2,Year);
			pst.setInt(3, courseId);
			ResultSet set =pst.executeQuery();
			while(set.next()) {
				model.setLowestPresentMonth(set.getInt("lowest"));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lowAttendance;
	}
	
	
	public static List<AttendanceChartModel> getAttendanceChartData(int Month,int Year,int courseId) {
		List<AttendanceChartModel> modelCollection = new ArrayList<>();
		
		
		try {
			Connection con = EstablishConnection.getConnection();
			String query = "select attendance_date,count(distinct student_id) as present_student from attendance where month(attendance_date)=? and year(attendance_date)=? and status=1 and course_id=? group by attendance_date";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, Month);
			pst.setInt(2,Year);
			pst.setInt(3, courseId);
			ResultSet set = pst.executeQuery();
			
			while(set.next()) {
				Date date = set.getDate("attendance_date");
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				
				int totalPresent = set.getInt("present_student");
				
				int gotDay = calendar.get(Calendar.DATE);
				AttendanceChartModel model = new AttendanceChartModel();
				model.setDay(gotDay);
				model.setTotalPresent(totalPresent);
				
				modelCollection.add(model);
	
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return modelCollection;
		
	}
}
