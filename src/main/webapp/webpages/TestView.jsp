<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file = "reusable_comp/ImportHeaders.jsp" %>
<title>Insert title here</title>
<style>
	.testCardBtn{
		width:100%;
		background: linear-gradient(90deg,#4074C0,#12BAEB);
	}
</style>
</head>
<body>
	<%@ include file="reusable_comp/CheckLogin.jsp" %>
	<%@ include file="reusable_comp/NavBar.jsp" %>
	<div class="center_div">
		<h2>Select course to view Test</h2>
		<select id="selectCourse" style="width:auto">
		</select>
		<button id="getTestBtn">Get Tests</button>
		<h2 class="courseHead">Course Heading</h2>
		<button class="successButton" id="addTestBtn">Add Tests</button>
		<div class="carpet">
			
		</div>
	</div>
	
	<script>
		$(document).ready(function(){
			var courseArray=[];
			var courseIdArray=[];
			$.ajax({
				url:'myCourse',
				method:'get',
				success:function(response){
					
					var options = "";
					for(i=0;i<response.length;i++){
						
						options=options+"<option value='"+response[i].courseId+"'>"+response[i].courseName+"_"+response[i].courseCollege+"("+response[i].courseFaculty+","+response[i].courseSemester+")</option>";
						courseArray.push(response[i].courseName+"_"+response[i].courseCollege+"("+response[i].courseFaculty+","+response[i].courseSemester+")");
						courseIdArray.push(response[i].courseId);
					}
					$("#selectCourse").html(options);
					
				},
				error:function(){
					
				}
			})
			
			$("#getTestBtn").on('click',function(){
				var course_id=$('#selectCourse').val();
				$(".courseHead").empty();
				$(".courseHead").html($('#selectCourse option:selected').text());
				const data = {
						courseId:course_id,
				}
				$.ajax({
					url:'myTest',
					method:'get',
					data:data,
					success:function(response){
						console.log(response)
						$(".carpet").empty();
						if(response.length==0){
							
						}else{
							
							for(i=0;i<response.length;i++){
								var card="<div class='card'>";
								var testName=card+"<h3>TestName:"+" "+response[i].testName+"</h3>";
								var fullMarks=testName+"<p>FullMarks:"+response[i].fullMarks+"</p>";
								var passMarks = fullMarks+"<p>PssMarks: "+response[i].passMarks+"</p>";
								var view = passMarks+"<button class='testCardBtn'>View</button>";
								var edit = view+"<button class='successButton testCardBtn'>Edit</button>";
								var remove = edit+"<button class='dangerButton testCardBtn'>Remove</button>";
								$(".carpet").append(remove);
							}
						}
						
					},
					error:function(response){
						
					}
				})
			})
			
			$("#addTestBtn").on('click',function(){
				courseId = $('#selectCourse').val();
				console.log(courseId);
			})
			
		})
	</script>
</body>
</html>