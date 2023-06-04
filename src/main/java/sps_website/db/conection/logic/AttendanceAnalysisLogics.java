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
	
	public static AttendanceModel getStudentAttendanceAnalysis(int courseId,int Month,int Year,AttendanceModel passedModel) {
		AttendanceModel model = passedModel;
		try {
			Connection con = EstablishConnection.getConnection();
			String query = "select s.student_id,s.name,count(a.student_id) as present_count from student s left join attendance a on s.student_id=a.student_id and a.status=1 where s.course_id=? and month(a.attendance_date)=? and year(a.attendance_date)=? group by s.student_id,s.name order by present_count desc";
			PreparedStatement pst =con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			pst.setInt(1,courseId);
			pst.setInt(2,Month);
			pst.setInt(3, Year);
			ResultSet set = pst.executeQuery();
			
			while(set.next()) {
				String highPresentName = set.getString("name");
				int highPresentId = set.getInt("student_id");
				int highPresentCount = set.getInt("present_count");
				
				System.out.println("nameH:"+highPresentName);
				set.last();
				
				String lowPresentName = set.getString("name");
				int lowPresentId = set.getInt("student_id");
				int lowPresentCount = set.getInt("present_count");
				System.out.println("nameL:"+lowPresentName);
				
				model.setHighPresentName(highPresentName);
				model.setHighPresentId(highPresentId);
				model.setHighPresentCount(highPresentCount);
				
				model.setLowPresentName(lowPresentName);
				model.setLowPresentId(lowPresentId);
				model.setLowPresentCount(lowPresentCount);
				
				
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}
	
	public static AttendanceModel getAllTimeAnalysis(int courseId,int Month,int Year,AttendanceModel passedModel) {
		AttendanceModel model = passedModel;
		try {
			Connection con = EstablishConnection.getConnection();
			String query = "select s.student_id,s.name,count(a.student_id) as present_count from student s left join attendance a on s.student_id=a.student_id and a.status=1 where s.course_id=? group by s.student_id,s.name order by present_count desc";
			PreparedStatement pst =con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			pst.setInt(1,courseId);
			
			ResultSet set = pst.executeQuery();
			
			while(set.next()) {
				String highPresentName = set.getString("name");
				int highPresentId = set.getInt("student_id");
				int highPresentCount = set.getInt("present_count");
				
				System.out.println("nameH:"+highPresentName);
				set.last();
				
				String lowPresentName = set.getString("name");
				int lowPresentId = set.getInt("student_id");
				int lowPresentCount = set.getInt("present_count");
				System.out.println("nameL:"+lowPresentName);
				
				model.setAllTimeHighName(highPresentName);
				model.setAllTimeHighId(highPresentId);
				model.setAllTimeHighCount(highPresentCount);
				
				model.setAllTimeLowName(lowPresentName);
				model.setAllTimeLowId(lowPresentId);
				model.setAllTimeLowCount(lowPresentCount);
				
				
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}
	
	public static AttendanceModel getTotalClassDays(int courseId,AttendanceModel passedModel) {
		AttendanceModel model = passedModel;
		try {
			Connection con = EstablishConnection.getConnection();
			String query = "select count(distinct attendance_date) as total_class_days from attendance where course_id=?";
			PreparedStatement pst =con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			pst.setInt(1,courseId);
			
			ResultSet set = pst.executeQuery();
			
			while(set.next()) {
				
				int totalClass = set.getInt("total_class_days");
				
				model.setTotalClassDays(totalClass);
				
				
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}
}
