package sps_website.db.conection.logic;
import java.sql.*;
import java.util.*;
import sps_website.db.conection.*;
import sps_website.model.*;

public class StudentLogics {
	public static boolean insertStudent(String StudentName,String studentEmail,int courseId,int age,String ethnicity,String gender,String status) throws ClassNotFoundException, SQLException {
		boolean result=false;
		Connection con= EstablishConnection.getConnection();
		String query = "insert into student(name,email,age,ethnicity,gender,course_id,student_status) values(?,?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, StudentName);
		pst.setString(2, studentEmail);
		pst.setInt(3,age);
		pst.setString(4,ethnicity);
		pst.setString(5,gender);
		pst.setInt(6,courseId);
		pst.setString(7,status);
		
		int rowAffected = pst.executeUpdate();
		
		if(rowAffected>0) {
			result=true;
		}
		
		return result;
	}
	
	public static List<StudentModel> getMyStudents(int courseId) throws ClassNotFoundException, SQLException {
		List<StudentModel> model = new ArrayList<>();
		Connection con = EstablishConnection.getConnection();
		String query = "select * from student where course_id=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1,courseId);
		
		ResultSet set = pst.executeQuery();
		
		while(set.next()) {
			int studentId = set.getInt("student_id");
			String name = set.getString("name");
			int age = set.getInt("age");
			String ethnicity = set.getString("ethnicity");
			String gender = set.getString("gender");
			String studentStatus = set.getString("student_status");
			int course_Id = set.getInt("course_id");
			String email = set.getString("email");
			StudentModel obj = new StudentModel(studentId,name,age,ethnicity,gender,studentStatus,course_Id,email);
			model.add(obj);
		}
		
		return model;
	}
	
	public static boolean isMyStudent(int userId,int studentId) throws ClassNotFoundException, SQLException {
		boolean status = false;
		
		Connection con = EstablishConnection.getConnection();
		String query = " select student.name from student join course on student.course_id=course.course_id where student_id=? and user_id=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, studentId);
		pst.setInt(2, userId);
		ResultSet set = pst.executeQuery();
		
		if(set.next()) {		
			status=true;
		}	
		return status;
	}
	
	public static boolean removeStudent(int studentId) throws ClassNotFoundException, SQLException {
		boolean status=false;
		
		Connection con = EstablishConnection.getConnection();
		String query = "delete from student where student_id=?";
		String query2 = "delete from attendance where student_id=?";
		String query3 = "delete from marks_allocation where student_id=?";
		String query4 = "delete from feedback where student_id=?";
		PreparedStatement pst4 = con.prepareStatement(query4);
		PreparedStatement pst3 = con.prepareStatement(query3);
		PreparedStatement pst2 = con.prepareStatement(query2);
		PreparedStatement pst = con.prepareStatement(query);
		
		pst4.setInt(1, studentId);
		pst3.setInt(1, studentId);
		pst2.setInt(1, studentId);
		pst.setInt(1, studentId);
		
		pst4.executeUpdate();
		pst3.executeUpdate();
		pst2.executeUpdate();
		int rowAffected = pst.executeUpdate();
		
		if(rowAffected>0) {
			status=true;
		}
		
		
		return status;
	}
	
	public static boolean updateStudent(int student_id,String studentName,int age,String ethnicity,String gender,int courseId,String studentStatus,String email) throws ClassNotFoundException, SQLException {
		boolean result = false;
		
		Connection con = EstablishConnection.getConnection();
		String query="update student set name=?,email=?,age=?,ethnicity=?,gender=?,course_id=?,student_status=? where student_id=?";
		String attendanceQuery = "update attendance set course_id=? where student_id=?";
		
		int checkCourse = getStudentCourseId(student_id);
		
		if(checkCourse!=courseId) {
			String deleteTest="delete from marks_allocation where student_id=?";
			PreparedStatement pst3 = con.prepareStatement(deleteTest);
			pst3.setInt(1,student_id);
			pst3.executeUpdate();
		}
		
		PreparedStatement pst2 = con.prepareStatement(attendanceQuery);
		PreparedStatement pst = con.prepareStatement(query);
		
		pst2.setInt(1, courseId);
		pst2.setInt(2,student_id);
		pst2.executeUpdate();
		
		pst.setString(1,studentName);
		pst.setString(2,email);
		pst.setInt(3,age);
		pst.setString(4,ethnicity);
		pst.setString(5,gender);
		pst.setInt(6, courseId);
		pst.setString(7,studentStatus);
		pst.setInt(8,student_id);
		
		int rowAffected=pst.executeUpdate();
		if(rowAffected>0) {
			result=true;
		}
	
		
		
		return result;
	}
	
	
	public static int getStudentCourseId(int student_id) throws ClassNotFoundException, SQLException {
		Connection con = EstablishConnection.getConnection();
		int courseId=0;
		String query = "select * from student where student_id=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1,student_id);
		ResultSet set = pst.executeQuery();
		while(set.next()) {
			courseId=set.getInt("course_id");
		}
		
		return courseId;
	}
	
	public static StudentModel getParticularStudent(int student_id,StudentModel student) throws ClassNotFoundException, SQLException {
		
		Connection con = EstablishConnection.getConnection();
		String query = "select * from student where student_id=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, student_id);
		ResultSet set = pst.executeQuery();
		
		while(set.next()) {
			student.setStudentId(student_id);
			student.setStudentName(set.getString("name"));
			student.setAge(set.getInt("age"));
			student.setEthnicity(set.getString("ethnicity"));
			student.setGender(set.getString("gender"));
			student.setCourseId(set.getInt("course_id"));
			student.setStudentStatus(set.getString("student_status"));
			student.setEmail(set.getString("email"));
		}
		student = getStudentAttendanceTotal(student);
		return student;
	}
	
	public static StudentModel getStudentAttendanceTotal(StudentModel student) throws ClassNotFoundException, SQLException {
		
		Connection con = EstablishConnection.getConnection();
		String totalQuery = "select count(*) as totalClass from attendance where course_id=? and student_id=? ";
		PreparedStatement totalPst = con.prepareStatement(totalQuery);
		totalPst.setInt(1, student.getCourseId());
		totalPst.setInt(2, student.getStudentId());
		ResultSet Totalset = totalPst.executeQuery();
		
		while(Totalset.next()) {
			student.setTotalAttendance(" Out of "+Totalset.getInt("totalClass"));
		}
		
		String specificQuery = "select count(*) as specificPresent from attendance where course_id=? and student_id=? and status=1";
		PreparedStatement specificPst = con.prepareStatement(specificQuery);
		specificPst.setInt(1, student.getCourseId());
		specificPst.setInt(2, student.getStudentId());
		ResultSet specSet = specificPst.executeQuery();
		
		while(specSet.next()) {
			student.setTotalAttendance(student.getTotalAttendance()+" days : "+specSet.getInt("specificPresent"));
		}
		
		return student;
	}
	
}
