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
		<input type="date" id="selectDate"/>
		<button id="setAttendanceBtn">Set attendance</button>
		
		<div class="mycourse">
			<h2 id="courseHead">Set Attendance</h2>
			<table>
	            <thead>
	                <tr>
	                    <th>Sno.</th>
	                    <th>Name</th>
	                    <th>Status</th>
	                </tr>
	            </thead>
	            <tbody id="tableBody">

	            </tbody>
	        </table>
		</div>
	</div>
	<script>
	$(document).ready(function(){
		var today = new Date();
		var formattedDate = today.toISOString().slice(0, 10);
		document.getElementById("selectDate").value = formattedDate;
		var gotCourseId=0;
		var gotDate="";
		
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
		});
		
		$('#setAttendanceBtn').on('click',function(){
			var date = $('#selectDate').val();
			var courseId = $('#selectCourse').val();
			if(date==""){
				Swal.fire(
						  'Date is not selected',
						  'Please select a date!',
						  'error'
						)
			}else{
				console.log("the date is "+date+" with course "+courseId);
				const formData = {
						courseId:courseId,
						date:date
				}
				getStudents(formData);
				
			}
			
		})
		
		
		$('#tableBody').on('click','.successButton',function(){
			studentId = $(this).val();
			handleAttendance(studentId,1);
			$(this).removeClass('successButton').addClass('dangerButton');
			$(this).text('set absent');
		})
		
		$('#tableBody').on('click','.dangerButton',function(){
			studentId = $(this).val();
			handleAttendance(studentId,0);
			$(this).removeClass('dangerButton').addClass('successButton');
			$(this).text('set present')
			
		})
		
		
		function handleAttendance(studentId,attendanceStatus){
			const data = {
					courseId:gotCourseId,
					studentId:studentId,
					attendanceStatus:attendanceStatus,
					date:gotDate,
			}
			const jsonData= JSON.stringify(data);
			$.ajax({
				url:'myAttendance',
				method:'post',
				data:jsonData,
				success:function(response){
					console.log(response);
				},
				error:function(){
					
				}
			})
			
		}
		
		
		
		
		function getStudents(formData){
			$.ajax({
				url:'myAttendance',
				method:'get',
				data:formData,
				success:function(response){
					$('#tableBody').empty();
					console.log(response);
					var attendance="";
					for(i=0;i<response.length;i++){
						var sno = "<tr><td>"+(i+1)+"</td>";
						var name = sno+"<td>"+response[i].studentName+"</td>";
						if(response[i].attendanceStatus==0){
							attendance = name+"<td><button class='successButton' value='"+response[i].studentId+"' >Set Present</button></td></tr>";
						}else if (response[i].attendanceStatus==1){
							attendance = name+"<td><button class='dangerButton' value='"+response[i].studentId+"' >Set Absent</button></td></tr>";
						}
						$('#tableBody').append(attendance);
					}
					gotCourseId=$('#selectCourse').val();
					gotDate=$('#selectDate').val();
				},
				error:function(){
					
				}
			});
		}
		
		
	});
	
	
	</script>
</body>
</html>