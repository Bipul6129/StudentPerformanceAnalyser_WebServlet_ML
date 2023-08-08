package sps_website.db.conection.logic;
import sps_website.db.conection.*;
import java.sql.*;
import java.util.*;
import sps_website.model.*;
public class StudentLoginLogic {
	public static List<StudentPortalModel> checkLogin(String email) {
		List<StudentPortalModel> model = new ArrayList<>();
		try {
			Connection conn = EstablishConnection.getConnection();
			
			String query = "select course.course_id,student.student_id,course.courseName,course.faculty,student.name from student inner join course on student.course_id=course.course_id where student.email=?";
			PreparedStatement pst = conn.prepareStatement(query);
			pst.setString(1,email);
			ResultSet result = pst.executeQuery();
			
			while(result.next()) {
				int course_id=result.getInt("course_id");
				int student_id=result.getInt("student_id");
				String courseName = result.getString("courseName");
				
				StudentPortalModel singleModel = new StudentPortalModel(course_id,student_id,courseName);
				model.add(singleModel);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}
}
