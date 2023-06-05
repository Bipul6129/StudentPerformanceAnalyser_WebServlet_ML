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
}
