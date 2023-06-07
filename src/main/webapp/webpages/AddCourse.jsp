<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sps_website.model.UserModel" errorPage="ErrorPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<%@ include file = "reusable_comp/ImportHeaders.jsp" %>
<style>
	select{
		width:17em;
	}
</style>
</head>
<body>
	<div id="blanker">
		<%@ include file="reusable_comp/CheckLogin.jsp" %>
		<%@ include file = "reusable_comp/NavBar.jsp" %>
		<div class="mycourse">
	        <h2 class="pageHeading">My Course</h2>
	        <table>
	            <thead>
	                <tr>
	                    <th>Sno.</th>
	                    <th>CourseName</th>
	                    <th>Faculty</th>
	                    <th>Semester</th>
	                    <th>College Name</th>
	                    
	                    <th>Staus</th>
	                    <th></th>
	                </tr>
	            </thead>
	            <tbody id="tableBody">

	            </tbody>
	        </table>
	        <button id="addcourse">Add Course</button>
	        
	    </div>
	    </div>
	    <div class="center">
	        <div class="popup">
	            <div class="close-btn">&times;</div>
	            <div class="form">
	                <h2>Add New COurse</h2>
	                <form id="insertCourseForm">
		                <div class="form-element">
		                    <label for="coursename">CourseName</label>
		                    <input type="text" name="courseName" id="courseName" placeholder="Enter new COurseName"/>
		                </div>
		                <div class="form-element">
		                    <label for="faculty">Faculty</label>
		                    <input type="text" name="courseFaculty" id="courseFaculty" placeholder="Enter faculty"/>
		                </div>
		                <div class="form-element">
		                    <label for="semester">Semester</label>
		                    <input type="text" name="courseSemester" id="courseSemester" placeholder="Enter semester"/>
		                </div>
		                <div class="form-element">
		                    <label for="semester">College Name</label>
		                    <select name="collegeDropDown" id="collegeDropBox">
		                    	
		                    </select>
		                </div>
		                <button type="submit">Save</button>
	                </form>
	                
	    
	            </div>
	        </div>
	    </div>
	</div>
    <%@ include file="js/addCourseJS.jsp" %>
</body>
</html>