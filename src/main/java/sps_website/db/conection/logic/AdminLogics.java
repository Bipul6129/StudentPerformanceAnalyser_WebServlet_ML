package sps_website.db.conection.logic;
import java.util.*;
import sps_website.model.*;
import sps_website.db.*;
import java.sql.*;
import java.sql.Date;
import java.time.*;
import sps_website.db.conection.EstablishConnection;

public class AdminLogics {
	public static List<UserSpecModel> getUsers() {
		List<UserSpecModel> userModel = new ArrayList<>();
		try {
			Connection conn = EstablishConnection.getConnection();
			String query = "select * from user where isAdmin=0";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet result = pst.executeQuery();
			while(result.next()) {
				String userName = result.getString("userName");
				String email = result.getString("email");
				int isBlocked = result.getInt("isBlocked");
				int courseCount = getCount(conn,result.getInt("user_id"));
				int userId = result.getInt("user_id");
				UserSpecModel model = new UserSpecModel(userName,email,courseCount,isBlocked,userId);
				userModel.add(model);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userModel;
	}
	
	public static int getCount(Connection conn,int userId) {
		String query = "select count(*) as user_course from course where user_id=?";
		int courseCount=0;
		try {
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1,userId);
			ResultSet result = pst.executeQuery();
			while(result.next()) {
				courseCount = result.getInt("user_course");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return courseCount;
		
	}
	
	public static boolean setBlockStatus(int userId,int blockStatus) {
		boolean status=false;
		try {
			Connection conn =EstablishConnection.getConnection();
			String query = "update user set isBlocked=? where user_id=?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setInt(1,blockStatus);
			pst.setInt(2,userId);
			int affected=pst.executeUpdate();
			if(affected>0) {
				status= true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
}
