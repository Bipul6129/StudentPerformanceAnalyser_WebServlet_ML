package sps_website.db.conection.logic;
import sps_website.db.conection.*;
import java.sql.*;
public class InsertCourseLogic {
	
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
}
