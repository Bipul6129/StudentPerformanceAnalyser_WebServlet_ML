package sps_website.db.conection.logic;
import sps_website.db.conection.*;

import java.util.*;
import java.sql.*;
import sps_website.model.*;
public class MarksAllocation {
	public static List<MarksSheetModel> getMarks(int testId) throws ClassNotFoundException, SQLException {
		List<MarksSheetModel> model = new ArrayList<>();
		
		Connection con = EstablishConnection.getConnection();
		String query = "select * from marks_allocation where test_id=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, testId);
		
		ResultSet set = pst.executeQuery();
		while(set.next()) {
			int studentId = set.getInt("student_id");
			int marksObtained = set.getInt("marks_obtained");
			int test_Id = set.getInt("test_id");
			
			MarksSheetModel obj = new MarksSheetModel(studentId,marksObtained,test_Id);
			model.add(obj);
		}
		
		return model;
	}
	
	public static boolean insertMarks(MarksSheetModel model) throws ClassNotFoundException, SQLException {
		boolean status = true;
		
		Connection con = EstablishConnection.getConnection();	
		String query = "insert into marks_allocation(student_id,marks_obtained,test_id) values(?,?,?)";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1,model.getStudentId());
		pst.setInt(2,model.getMarksObtained());
		pst.setInt(3,model.getTestId());
		
		int rowAffected = pst.executeUpdate();
		if(rowAffected>0) {
			status=true;
		}
		
		return status;
	}
	
	public static boolean updateMarks(int studentId,int marksObtained,int testId) throws ClassNotFoundException, SQLException {
		boolean status = false;
		
		Connection con = EstablishConnection.getConnection();
		String query = "update marks_allocation set marks_obtained=? where student_id=? and test_id=?";
		PreparedStatement pst = con.prepareStatement(query);
		
		pst.setInt(1,marksObtained);
		pst.setInt(2, studentId);
		pst.setInt(3, testId);
		
		int rowAffected = pst.executeUpdate();
		if(rowAffected>0) {
			status=true;
		}
		return status;
	}

}

