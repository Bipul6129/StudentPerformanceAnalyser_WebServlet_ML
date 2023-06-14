package sps_website.db.conection.logic;
import sps_website.model.*;

import java.sql.*;
import java.util.*;
import sps_website.db.conection.*;
public class MyTestLogics {
	
	public static List<TestModel> getMyTest(int courseId) throws ClassNotFoundException, SQLException{
		
		List<TestModel> model = new ArrayList<>();
		Connection con = EstablishConnection.getConnection();
		String query = "select * from test where course_id=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1,courseId);
		ResultSet set = pst.executeQuery();
		while(set.next()) {
			int testId = set.getInt("test_id");
			String testName = set.getString("testName");
			int fullMarks = set.getInt("full_marks");
			int passMarks = set.getInt("pass_marks");
			int course_Id = set.getInt("course_id");
			
			TestModel obj = new TestModel(testId,testName,fullMarks,passMarks,course_Id);
			model.add(obj);
		}
		return model;
		
	}
	
	public static boolean insertTest(TestModel model) throws ClassNotFoundException, SQLException {
		boolean status = false;
		
		Connection con = EstablishConnection.getConnection();
		String query = "insert into test(testName,full_marks,pass_marks,course_id) values(?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1,model.getTestName());
		pst.setInt(2,model.getFullMarks());
		pst.setInt(3,model.getPassMarks());
		pst.setInt(4, model.getCourseId());
		
		int rowAffected = pst.executeUpdate();
		
		if(rowAffected>0) {
			status=true;
		}
		
		
		return status;
	}
	
	public static boolean deleteTest(int test_id,int course_id) throws ClassNotFoundException, SQLException {
		boolean status=false;
		
		Connection con = EstablishConnection.getConnection();
		String query = "delete from test where test_id=? and course_id=?";
		String deleteAllocation = "delete from marks_allocation where test_id=?";
		PreparedStatement pst2 = con.prepareStatement(deleteAllocation);
		PreparedStatement pst = con.prepareStatement(query);
		
		pst2.setInt(1,test_id);
		pst.setInt(1, test_id);
		pst.setInt(2, course_id);
		
		pst2.executeUpdate();
		int rowAffected = pst.executeUpdate();
		
		if(rowAffected>0) {
			status=true;
		}
				
		
		return status;
	}
	
	public static boolean editTest(TestModel model) throws ClassNotFoundException, SQLException {
		boolean status =false;
		
		Connection con = EstablishConnection.getConnection();
		String query = "update test set testName=?,full_marks=?,pass_marks=? where test_id=? and course_id=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setString(1, model.getTestName());
		pst.setInt(2, model.getFullMarks());
		pst.setInt(3, model.getPassMarks());
		pst.setInt(4,model.getTestId());
		pst.setInt(5,model.getCourseId());
		
		int rowAffected = pst.executeUpdate();
		
		if(rowAffected>0) {
			status=true;
		}
		
		return status;
	}
	
	public static TestModel getSpecificTest(int test_id) throws ClassNotFoundException, SQLException {
		TestModel model = new TestModel();
		
		Connection con = EstablishConnection.getConnection();
		String query = "select * from test wehre test_id=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, test_id);
		
		ResultSet set=pst.executeQuery();
		while(set.next()) {
			model.setTestName(set.getString("testName"));
			model.setFullMarks(set.getInt("full_marks"));
			model.setPassMarks(set.getInt("pass_marks"));
			model.setCourseId(set.getInt("course_id"));
			
		}
		
		return model;
	}
	
	
}
