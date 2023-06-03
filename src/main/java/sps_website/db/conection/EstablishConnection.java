package sps_website.db.conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EstablishConnection {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sps_website","root","root");
			return con;
		}catch(Exception e) {
			return null;
		}
	}
}
