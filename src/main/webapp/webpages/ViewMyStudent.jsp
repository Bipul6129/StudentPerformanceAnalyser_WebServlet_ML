<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="reusable_comp/ImportHeaders.jsp" %>
<title>Insert title here</title>
<style>
	#selectCourse{
		width:auto;
	}
</style>
</head>
<body>
	<%@ include file="reusable_comp/CheckLogin.jsp" %>
	<%@ include file="reusable_comp/NavBar.jsp" %>
	<div class="center_div">
		<h2>View Students</h2>
		<select id="selectCourse">
		</select>
		<button class="successButton" id="getStudentBtn">get students</button>
		<div class="mycourse">
			<h2 id="courseHead">Select Course</h2>
			<table>
	            <thead>
	                <tr>
	                    <th>Sno.</th>
	                    <th>Name</th>
	                    <th>Age</th>
	                    <th>Gender</th>

	                </tr>
	            </thead>
	            <tbody id="tableBody">

	            </tbody>
	        </table>
		</div>
	</div>
	
	<script src="js/viewMyStudentJS.js">
		
	</script>
</body>
</html>