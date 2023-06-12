<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="reusable_comp/ImportHeaders.jsp" %>
<style>
	#selectCourse{
		width:auto;
	}
</style>
<title>Insert title here</title>
</head>
<body>
	<%@ include file="reusable_comp/CheckLogin.jsp" %>
	<%@ include file="reusable_comp/NavBar.jsp" %>
	<div class="center_div">
		<select id="selectCourse">
		</select>
		<input type="date"/>
	</div>
	<script>
	$(document).ready(function(){
		var courseArray=[];
		var courseIdArray=[];
		$.ajax({
			url:'myCourse',
			method:'get',
			success:function(response){
				console.log(response);
				var options = "";
				for(i=0;i<response.length;i++){
					console.log(response[i].courseName);
					options=options+"<option value='"+response[i].courseId+"'>"+response[i].courseName+"_"+response[i].courseCollege+"("+response[i].courseFaculty+","+response[i].courseSemester+")</option>";
					courseArray.push(response[i].courseName+"_"+response[i].courseCollege+"("+response[i].courseFaculty+","+response[i].courseSemester+")");
					courseIdArray.push(response[i].courseId);
				}
				$("#selectCourse").html(options);
				
			},
			error:function(){
				
			}
		})
	});
	
	
	</script>
</body>
</html>