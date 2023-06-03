package sps_website.db.conection.logic;
import java.sql.*;
import at.favre.lib.crypto.bcrypt.*;
import sps_website.db.conection.*;
public class RegisterLogic {
	public static boolean RegisterUser(String email,String username,String password) {
		boolean status = false;
		
		try {
			Connection con = EstablishConnection.getConnection();
			String Emailquery = "select * from user where email=?";
			PreparedStatement pst1 = con.prepareStatement(Emailquery);
			pst1.setString(1,email);
			ResultSet emailGot = pst1.executeQuery();
			if(!emailGot.next()) {
				String hashedPassword = BCrypt.withDefaults().hashToString(12,password.toCharArray());
				String query = "insert into user(userName,isAdmin,password,email) values(?,?,?,?)";
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, username);
				pst.setInt(2,0);
				pst.setString(3,hashedPassword);
				pst.setString(4,email);
				int RowAffected = pst.executeUpdate();
				if(RowAffected>0) {
					status=true;
				}
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
}
