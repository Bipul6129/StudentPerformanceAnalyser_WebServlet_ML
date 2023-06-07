package sps_website.db.conection.logic;
import java.sql.*;
import sps_website.db.conection.*;

public class StudentLogics {
	public static boolean insertStudent(String StudentName,int courseId,int age,String ethnicity,String gender,String status) throws ClassNotFoundException, SQLException {
		boolean result=false;
		Connection con= EstablishConnection.getConnection();
		String query = "insert into student(name,age,ethnicity,gender,course_id,student_status) values(?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, StudentName);
		pst.setInt(2,age);
		pst.setString(3,ethnicity);
		pst.setString(4,gender);
		pst.setInt(5,courseId);
		pst.setString(6,status);
		
		int rowAffected = pst.executeUpdate();
		
		if(rowAffected>0) {
			result=true;
		}
		
		return result;
	}
}
