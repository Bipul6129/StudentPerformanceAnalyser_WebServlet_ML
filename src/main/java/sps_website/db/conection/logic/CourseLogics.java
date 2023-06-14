package sps_website.db.conection.logic;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import sps_website.model.*;
import sps_website.db.conection.*;
public class CourseLogics {
	
	public static List<CourseModel> getMyCourse(int userId) {
		
		List<CourseModel> myCourses = new ArrayList<>();
		
		try {
			Connection con = EstablishConnection.getConnection();
			String query = "select course.college_id,course.course_id,course.courseName,course.faculty,course.semester,course.status,college.collegeName from course join college on course.user_id = college.user_id and college.college_id=course.college_id where course.user_id=? order by course.status desc,course.courseName asc";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, userId);
			ResultSet allCourses = pst.executeQuery();
			
			while(allCourses.next()) {
				int course_id = allCourses.getInt("course_id");
				String courseName = allCourses.getString("courseName");
				String faculty = allCourses.getString("faculty");
				String semester = allCourses.getString("semester");
				int status = allCourses.getInt("status");
				String collegeName = allCourses.getString("collegeName");
				int courseCollegeId = allCourses.getInt("college_id");
				CourseModel course = new CourseModel(course_id,courseName,faculty,semester,collegeName,status,courseCollegeId);
				myCourses.add(course);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return myCourses;
	}
	
	public static boolean insertCourse(String courseName,String faculty,String semester,int collegeId,int userId){
		boolean status=false;
		try {
			Connection con = EstablishConnection.getConnection();
			String query = "insert into course(courseName,faculty,semester,college_id,status,user_id) values(?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1,courseName);
			pst.setString(2, faculty);
			pst.setString(3, semester);
			pst.setInt(4, collegeId);
			pst.setInt(5,1);
			pst.setInt(6, userId);
			int rowAffected = pst.executeUpdate();
			
			if(rowAffected>0) {
				status=true;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static boolean updateCourse(String courseName,String faculty,String semester,int collegeId,int course_status,int courseId){
		boolean status=false;
		try {
			Connection con = EstablishConnection.getConnection();
			String query = "update course set courseName=?,faculty=?,semester=?,college_id=?,status=? where course_id=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1,courseName);
			pst.setString(2, faculty);
			pst.setString(3, semester);
			pst.setInt(4, collegeId);
			pst.setInt(5,course_status);
			pst.setInt(6, courseId);
			int rowAffected = pst.executeUpdate();
			
			if(rowAffected>0) {
				status=true;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public static boolean deleteCourse(int courseId){
		boolean status=false;
		try {
			Connection con = EstablishConnection.getConnection();
			String query = "delete from course where course_id=?";
			String deleteStudent = "delete from student where course_id=?";
			String deleteCourse = "delete from test where course_id=?";
			String deleteAttendance = "delete from attendance where course_id=?";
			
			PreparedStatement pst4 = con.prepareStatement(deleteAttendance);
			PreparedStatement pst3 =con.prepareStatement(deleteCourse);
			PreparedStatement pst2 = con.prepareStatement(deleteStudent);
			PreparedStatement pst = con.prepareStatement(query);
			
			pst.setInt(1, courseId);
			pst2.setInt(1, courseId);
			pst3.setInt(1, courseId);
			pst4.setInt(1, courseId);
			
			
			pst3.executeUpdate();
			pst4.executeUpdate();
			pst2.executeUpdate();
			
			int rowAffected = pst.executeUpdate();
			
			if(rowAffected>0) {
				status=true;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
}
