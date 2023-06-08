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
	
	<script>
		$(document).ready(function(){
			$.ajax({
				url:'myCourse',
				method:'get',
				success:function(response){
					console.log(response);
					var options = "";
					for(i=0;i<response.length;i++){
						console.log(response[i].courseName);
						options=options+"<option value='"+response[i].courseId+"'>"+response[i].courseName+"_"+response[i].courseCollege+"("+response[i].courseFaculty+","+response[i].courseSemester+")</option>";
					}
					$("#selectCourse").html(options);
				},
				error:function(){
					
				}
			})
			
			$("#getStudentBtn").on('click',function(){
				$("#tableBody").empty();
				$("#courseHead").empty();
				console.log($("#selectCourse").val());
				var course_id = $("#selectCourse").val();
				const data = {
						courseId:course_id,
				}
				const jsonData = JSON.stringify(data);
				console.log(jsonData);
				
				$.ajax({
					url:'myStudent',
					method:'get',
					data:data,
					success:function(response){
						console.log(response);
						$("#courseHead").append($("#selectCourse option:selected").text());
						for(i=0;i<response.length;i++){
							$("#tableBody").append("<tr><td>"+(i+1)+"</td><td>"+response[i].studentName+"</td><td>"+response[i].age+"</td><td>"+response[i].gender+"</td><td><button class='dangerButton' value='"+response[i].studentId+"'>Remove</button></td></tr>");	
							
						}
						
					},
					error:function(){
						
					}
				})
			});
			
			
			$("#tableBody").on('click','.dangerButton',function(){
				var studentId = $(this).val();
				console.log("student id for delete"+studentId);
				const data = {
					studentId:studentId,
				}
				const jsonData = JSON.stringify(data);
				
				$.ajax({
					url:'myStudent',
					method:'delete',
					data:jsonData,
					success:function(response){
						console.log(response);
					},
					error:function(){
						
					}
				})
			})
			
		})
	</script>
</body>
</html>