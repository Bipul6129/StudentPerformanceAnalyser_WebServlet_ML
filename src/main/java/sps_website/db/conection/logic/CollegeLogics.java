package sps_website.db.conection.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sps_website.db.conection.EstablishConnection;
import sps_website.model.MyCollegeModel;

public class CollegeLogics {
	public static List<MyCollegeModel> getMyCollege(int userId) throws ClassNotFoundException, SQLException{
		
		List<MyCollegeModel> collegeCollection = new ArrayList<>();
		
		Connection con = EstablishConnection.getConnection();
		String query = "select college_id,collegeName,location from college where user_id=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, userId);
		ResultSet set = pst.executeQuery();
		
		while(set.next()) {
			int collegeId = set.getInt("college_id");
			String collegeName = set.getString("collegeName");
			String location = set.getString("location");
			MyCollegeModel model = new MyCollegeModel(collegeId,collegeName,location);
			collegeCollection.add(model);
		}
		
		return collegeCollection;
	}
	
	public static boolean insertCollege(int userId,String collegeName,String location) throws ClassNotFoundException, SQLException {
		boolean status=false;
		Connection con = EstablishConnection.getConnection();
		String query = "insert into college(collegeName,location,user_id) values(?,?,?)";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1,collegeName);
		pst.setString(2,location);
		pst.setInt(3,userId);
		
		int rowAffected = pst.executeUpdate();
		
		if(rowAffected>0) {
			status=true;
		}
		
		return status;
	}
	
	public static boolean deleteCollege(int collegeId) throws ClassNotFoundException, SQLException {
		boolean status=false;
		Connection con = EstablishConnection.getConnection();
		String query = "delete from college where college_id=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1,collegeId);
		
		int rowAffected = pst.executeUpdate();
		
		if(rowAffected>0) {
			status=true;
		}
		
		return status;
	}
	
	public static boolean Courseexist(int collegeId) throws SQLException, ClassNotFoundException {
		boolean status=false;
		Connection con = EstablishConnection.getConnection();
		String query = "select * from course where college_id=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1,collegeId);
		
		ResultSet set = pst.executeQuery();
		
		if(set.next()) {
			status=true;
		}
		
		
		
		return status;
	}
}
