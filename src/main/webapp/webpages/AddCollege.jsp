<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sps_website.model.UserModel" errorPage="ErrorPage.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<%@ include file = "reusable_comp/ImportHeaders.jsp" %>

<body>
	<div id="blanker">
		<%@ include file="reusable_comp/CheckLogin.jsp" %>
		<%@ include file = "reusable_comp/NavBar.jsp" %>
		<div class="mycourse">
	        <h2 class="pageHeading">Colleges</h2>
	        <table>
	            <thead>
	                <tr>
	                    <th>Sno.</th>
	                    <th>CollegeName</th>
	                    <th>Location</th>
	                    <th>Course</th>
	                    <th></th>
	                </tr>
	            </thead>
	            <tbody id="tableBody">

	            </tbody>
	        </table>
	        <button id="addcourse">Add College</button>
	        
	    </div>
	    </div>
	    <div class="center">
	        <div class="popup">
	            <div class="close-btn">&times;</div>
	            <div class="form">
	                <h2>Add New College</h2>
	                <form id="insertCollegeForm">
		                <div class="form-element">
		                    <label for="coursename">CollegeName</label>
		                    <input type="text" id="collegeName" name="collegeName" placeholder="Enter new College Name"/>
		                </div>
		                <div class="form-element">
		                    <label for="faculty">Location</label>
		                    <input type="text" id="collegeLocation" name="collegeLocation" placeholder="Enter College Location"/>
		                </div>
		                
		                <button type="submit">Save</button>
	                </form>
	                
	    
	            </div>
	        </div>
	    </div>
	</div>
	
	<script src="js/addCollegeJS.js">
	
		
	</script>
    
</body>
</html>