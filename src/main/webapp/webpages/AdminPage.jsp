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
	<%@ include file="reusable_comp/CheckAdminLogin.jsp" %>
	<%@ include file="reusable_comp/NavBar.jsp" %>
	<div class="mycourse">
		<h2 class="oageHeading">List Users</h2>
		<table>
			<thead>
				<tr>
					<th>Sno.</th>
					<th>UserName</th>
					<th>Email</th>
					<th>No. of Course</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody id="tableBody">
			
			</tbody>
		</table>
	</div>
	<script>
		$(document).ready(function(){
			$.ajax({
				url:'adminData',
				method:'get',
				success:function(response){
					console.log(response);
					for(i=0;i<response.length;i++){
						var sno="<tr><td>"+(i+1)+"</td>";
						var userName=sno+"<td>"+response[i].username+"</td>";
						var email=userName+"<td>"+response[i].email+"</td>";
						var noCourse=email+"<td>"+response[i].numOfSubjects+"</td>";
						var action=noCourse;
						var blockStatus=response[i].isBlocked;
						if(blockStatus==0){
							action=action+"<td><button class='dangerButton' value='"+response[i].userId+"'>Block</button></td>";
						}else if(blockStatus==1){
							action=action+"<td><button class='successButton' value='"+response[i].userId+"'>UnBlock</button></td>";
						}
						$('#tableBody').append(action+'</tr>');
					}
				},
				error:function(){
					console.log('error');
				}
			});
			
			$('#tableBody').on('click','.dangerButton',function(){
				userId = $(this).val();
				console.log(userId);
				handleBlockStatus(userId,1);
				$(this).removeClass('dangerButton').addClass('successButton');
				$(this).text('UnBlock');
			})
			
			$('#tableBody').on('click','.successButton',function(){
				userId = $(this).val();
				handleBlockStatus(userId,0);
				$(this).removeClass('successButton').addClass('dangerButton');
				$(this).text('Block');
			})
			
			function handleBlockStatus(userId,blockStatus){
				const data={
						userId:userId,
						blockStatus:blockStatus
				}
				const jsonData=JSON.stringify(data);
				$.ajax({
					url:'adminData',
					method:'post',
					data:jsonData,
					success:function(response){
						console.log(response);
					},
					error:function(){
						
					}
				});
				
			}
			
		});
	</script>
</body>
</html>