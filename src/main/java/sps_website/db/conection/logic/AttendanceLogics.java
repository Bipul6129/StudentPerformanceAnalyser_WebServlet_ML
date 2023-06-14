package sps_website.db.conection.logic;
import java.util.*;
import sps_website.model.*;
import sps_website.db.*;
import java.sql.*;
import java.sql.Date;
import java.time.*;
import sps_website.db.conection.EstablishConnection;
public class AttendanceLogics {
	
	public static  List<StudentModel> getAttendanceofDay(String date,int courseId) throws ClassNotFoundException, SQLException {
		List<StudentModel> model = new ArrayList<>();
		
		Date tosendDate = Date.valueOf(date);
		
		
		Connection con = EstablishConnection.getConnection();
		String query = "select s.student_id,s.name,a.attendance_date,a.status from student s join attendance a on s.student_id=a.student_id where attendance_date=? and a.course_id=?";
		String getstudent = "select * from student where course_id=?";
		
		PreparedStatement pst = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		PreparedStatement pst2 = con.prepareStatement(getstudent);
		
		pst.setDate(1, tosendDate);
		pst.setInt(2, courseId);
		pst2.setInt(1, courseId);
		
		ResultSet attendanceSet = pst.executeQuery();
		ResultSet studentSet = pst2.executeQuery();
		
		int match=0;
		System.out.println("working till here");
		while(studentSet.next()) {
			
			while(attendanceSet.next()) {
				if(studentSet.getInt("student_id")==attendanceSet.getInt("student_id")) {
					match=1;
					System.out.println("it's a match");
					StudentModel obj = new StudentModel();
					obj.setStudentId(studentSet.getInt("student_id"));
					obj.setStudentName(studentSet.getString("name"));
					obj.setAttendanceStatus(attendanceSet.getInt("status"));
					obj.setCourseId(studentSet.getInt("course_id"));
					model.add(obj);
				}
				
			}
			if(match==0) {
				System.out.println("No match");
				StudentModel obj = new StudentModel();
				obj.setStudentId(studentSet.getInt("student_id"));
				obj.setStudentName(studentSet.getString("name"));
				obj.setAttendanceStatus(0);
				obj.setCourseId(studentSet.getInt("course_id"));
				model.add(obj);
				match=0;
			}else {
				match=0;
			}
			attendanceSet.beforeFirst();
			
			
		}
		
		return model;
		
	
	}
	
	public static boolean insertAttendance(int studentId,String date,int attendanceStatus,int courseId) throws ClassNotFoundException, SQLException {
		boolean status=false;
		Date toSend = Date.valueOf(date);
		boolean checkRow = rowExists(studentId,toSend,courseId);
		Connection con = EstablishConnection.getConnection();
		String query="";
		if(checkRow) {
			query="update attendance set status=? where student_id=? and course_id=? and attendance_date=?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, attendanceStatus);
			pst.setInt(2, studentId);
			pst.setInt(3, courseId);
			pst.setDate(4,toSend);
			int rowAffected=pst.executeUpdate();
			if(rowAffected>0) {
				status=true;
			}
		}else {
			query="insert into attendance values(?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, studentId);
			pst.setInt(2, courseId);
			pst.setInt(3, attendanceStatus);
			pst.setDate(4, toSend);
			int rowAffected=pst.executeUpdate();
			if(rowAffected>0) {
				status=true;
			}
		}
		return status;
		
	}

	public static void assignStudent(int courseId,String date) throws ClassNotFoundException, SQLException {
		Date toSend = Date.valueOf(date);
		Connection con = EstablishConnection.getConnection();
		String query="select * from student where course_id=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, courseId);
		ResultSet set = pst.executeQuery();
		while(set.next()) {
			int studentId = set.getInt("student_id");
			boolean exists = rowExists(studentId,toSend,courseId);
			if(!exists) {
				String attquery = "insert into attendance values(?,?,0,?)";
				PreparedStatement pst2 = con.prepareStatement(attquery);
				pst2.setInt(1, studentId);
				pst2.setInt(2,courseId);
				pst2.setDate(3, toSend);
				pst2.executeUpdate();
			}
			
		}
		
		
	}
	
	
	
	
	public static boolean rowExists(int studentId,Date date,int courseId) throws ClassNotFoundException, SQLException {
		boolean hasRow=false;
		Connection con = EstablishConnection.getConnection();
		String query = "select * from attendance where student_id=? and attendance_date=? and course_id=?";
		
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, studentId);
		pst.setDate(2, date);
		pst.setInt(3, courseId);
		
		ResultSet set = pst.executeQuery();
		
		if(set.next()) {
			hasRow=true;
		}

		return hasRow;
	}
	

	
	
}
