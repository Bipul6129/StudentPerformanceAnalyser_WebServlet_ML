package sps_website.db.conection.logic;
import sps_website.model.*;
import java.sql.*;
import sps_website.db.conection.*;
import java.util.*;
public class TestAnalysisLogics {

	public static List<TestChartModel> getChartData(int courseId) throws ClassNotFoundException, SQLException{
		List<TestChartModel> models = new ArrayList<>();
		
		Connection con = EstablishConnection.getConnection();
		String query = "select t.test_id,t.testName,t.full_marks,t.pass_marks,m.student_id,m.marks_obtained from test t join marks_allocation m on t.test_id=m.test_id where course_id=? order by t.test_id asc";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, courseId);
		ResultSet set = pst.executeQuery();
		
		int count=0;
		int testId=0;
		int newtestId=0;
		
		String prevTestName="";
		
		int full_marks=0;
		int pass_marks=0;
		
		int passStudents=0;
		int marks_Obtained=0;
		
		while(set.next()) {
			marks_Obtained=set.getInt("marks_obtained");
			if(count==0) {
				count++;
				testId=set.getInt("test_id");
				newtestId=set.getInt("test_id");
				full_marks=set.getInt("full_marks");
				pass_marks=set.getInt("pass_marks");
				prevTestName=set.getString("testName");
				
				
				if(marks_Obtained>=pass_marks) {
					passStudents++;
				}
				if(set.isLast()) {
					TestChartModel obj = new TestChartModel(prevTestName,passStudents);
					models.add(obj);
				}
			}else {
				newtestId=set.getInt("test_id");
				if(testId==newtestId) {
					if(marks_Obtained>=pass_marks) {
						passStudents++;
					}
				}else {
					
					TestChartModel obj = new TestChartModel(prevTestName,passStudents);
					models.add(obj);
					passStudents=0;
					if(marks_Obtained>=set.getInt("pass_marks")) {
						passStudents++;
					}
					count=0;
					
				}
				if(set.isLast()) {
					TestChartModel obj = new TestChartModel(prevTestName,passStudents);
					models.add(obj);
				}
			}
			

		}
		return models;
	}
	
	public static TestAnalysisModel getTestAnalysis(int courseId) throws ClassNotFoundException, SQLException {
		
		Connection con = EstablishConnection.getConnection();
		String query="select t.test_id,s.name,t.testName,t.full_marks,t.pass_marks,m.student_id,m.marks_obtained from test t join marks_allocation m on t.test_id=m.test_id join student s on s.student_id=m.student_id where t.course_id=? order by t.test_id desc, marks_obtained desc";
		PreparedStatement pst = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		pst.setInt(1, courseId);
		
		ResultSet set = pst.executeQuery();
		
		int recentHigh = 0;
		int recentLow=0;
		int recentFull=0;
		String RecenthighStudent="";
		String RecentlowStudent="";
		
		int testId=0; 
		int newtestId=0;
		
		while(set.next()) {
			recentHigh=set.getInt("marks_obtained");
			testId=set.getInt("test_id");
			recentFull=set.getInt("full_marks");
			break;
		}
		set.beforeFirst();
		while(set.next()) {
			newtestId=set.getInt("test_id");
			if(testId!=newtestId) {
				recentLow=recentLow;
				break;
			}else {
				recentLow=set.getInt("marks_obtained");
			}
		}
		set.beforeFirst();
		
		while(set.next()) {
			if(set.getInt("test_id")==testId && set.getInt("marks_obtained")==recentHigh) {
				RecenthighStudent = RecenthighStudent+set.getString("name")+",";
			}else if(set.getInt("test_id")==testId && set.getInt("marks_obtained")==recentLow) {
				RecentlowStudent = RecentlowStudent+set.getString("name")+",";
			}
		}
		
		System.out.print("The recent high is "+RecenthighStudent+" The recent low is "+RecentlowStudent);
		
		TestAnalysisModel model= new TestAnalysisModel(RecenthighStudent,RecentlowStudent,recentHigh,recentLow,recentFull); 
		return model;
	}
	
	public static List<StudentTestChartModel> getStudentTestChart(int studentId) throws ClassNotFoundException, SQLException{
		List<StudentTestChartModel> models = new ArrayList<>();
		Connection con = EstablishConnection.getConnection();
		String query = "select m.marks_obtained,m.student_id,t.testName,t.full_marks from test t join marks_allocation m on m.test_id=t.test_id where student_id=?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, studentId);
	
		ResultSet set = pst.executeQuery();
		
		float marks_obtained;
		float full_marks; 
		float point=0;
		while(set.next()) {
			marks_obtained=(float) set.getInt("marks_obtained");
			full_marks=(float) set.getInt("full_marks");
			point = (marks_obtained/full_marks)*10;
			StudentTestChartModel obj = new StudentTestChartModel(set.getString("testName"),point);
			models.add(obj);
		}
		
		return models;
	}
	
	
}
