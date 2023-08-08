<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<%@ include file="reusable_comp/ImportHeaders.jsp" %>
</head>
<body>
	<%@ include file="reusable_comp/CheckLogout.jsp" %>
	<%@ include file="reusable_comp/NavBar.jsp" %>
	<div class="center_div">
		<div class="carpet">
			<form id="loginForm">
				<input name="email" id="email" type="email"/>
				<button type="submit" id="checkBtn">Check</button>
			</form>
		</div>
		<div id="courseSelect">
			<select id="course">
			</select>
			<button id="checkBtn" class="successButton">Check</button>
		</div>
	</div>
	
	<script>
		$(document).ready(function(){
			$('#courseSelect').hide();
			$('#loginForm').submit(function(event){
				$('#checkBtn').hide();
				event.preventDefault();
				var formData=$(this).serialize();
				$.ajax({
					url:'portalServlet',
					method:'post',
					data:formData,
					success:function(response){
						console.log(response);
						if(response.length>0){
							$('#courseSelect').show();
							var options="";
							for(i=0;i<response.length;i++){
								console.log(response[i]);
								options=options+"<option value='"+response[i].course_id+","+response[i].student_id+","+response[i].courseName+"'>"+response[i].courseName+"</option>";
							}
							$('#course').html(options);
						}else{
							$('#checkBtn').show();
						}
						
						
					},
					error:function(){
						alert('failed');
					}
				})
			});
			
			$('#courseSelect').on('click','#checkBtn',function(){
				var selectedValue=$('#course').val();
				var detailArray = selectedValue.split(',');
				var courseId=detailArray[0];
				var studentId=detailArray[1];
				var courseName=detailArray[2];
				window.location.href= 'StudentProfile.jsp?'+'course_id='+courseId+'&student_id='+studentId+'&course='+courseName;
			})
		})
	</script>
</body>
</html>