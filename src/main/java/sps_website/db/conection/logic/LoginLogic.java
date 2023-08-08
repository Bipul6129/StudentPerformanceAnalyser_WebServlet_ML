package sps_website.db.conection.logic;
import at.favre.lib.crypto.bcrypt.*;
import sps_website.db.conection.EstablishConnection;
import sps_website.model.*;
import java.sql.*;

public class LoginLogic {
	public static UserModel loginCheck(String username,String password) throws ClassNotFoundException, SQLException {
		UserModel currentUser= null;
		Connection con = EstablishConnection.getConnection();
		String query = "select * from user where username=? and isBlocked=?";
		PreparedStatement pstm = con.prepareStatement(query);
		pstm.setString(1,username);
		pstm.setInt(2, 0);
		ResultSet set = pstm.executeQuery();
		
		if(set.next()) {
			BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(),set.getString("password"));
			if(result.verified==true) {
				currentUser = new UserModel(username,set.getInt("user_id"),set.getInt("isAdmin"));
				
				
			}
		}
		return currentUser;
		
	}
}
