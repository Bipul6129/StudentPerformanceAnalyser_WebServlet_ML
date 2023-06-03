package sps_website.db.conection.logic;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import sps_website.model.*;
import sps_website.db.conection.*;
public class GetMyCourseLogic {
	
	public static List<CourseModel> getMyCourse(int userId) {
		
		List<CourseModel> myCourses = new ArrayList<>();
		
		try {
			Connection con = EstablishConnection.getConnection();
			String query = "select course.college_id,course.course_id,course.courseName,course.faculty,course.semester,course.status,college.collegeName from course join college on course.user_id = college.user_id and college.college_id=course.college_id where course.user_id=?;";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, userId);
			ResultSet allCourses = pst.executeQuery();
			
			while(allCourses.next()) {
				int course_id = allCourses.getInt("course_id");
				String courseName = allCourses.getString("courseName");
				String faculty = allCourses.getString("faculty");
				String semester = allCourses.getString("semester");
				int status = allCourses.getInt("status");
				String collegeName = allCourses.getString("collegeName");
				int courseCollegeId = allCourses.getInt("college_id");
				CourseModel course = new CourseModel(course_id,courseName,faculty,semester,collegeName,status,courseCollegeId);
				myCourses.add(course);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return myCourses;
	}
	
}
