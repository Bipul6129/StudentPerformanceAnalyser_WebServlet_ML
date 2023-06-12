<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file = "reusable_comp/ImportHeaders.jsp" %>
<title>Insert title here</title>
</head>
<style>
	.shiftLeft{
		display:flex;
		flex-direction:column;
		align-items:flex-start;
		width:78%;
		font-size:22px;
		
	}
</style>
<body>
	<%@ include file="reusable_comp/CheckLogin.jsp" %>
	<%@ include file="reusable_comp/NavBar.jsp" %>
	<div class="center_div">
		<h2 id="testName"></h2>
		<div class="shiftLeft">
			<label id="testFull"></label>
			<label id="testPass"></label>
		</div>
		<div class="mycourse">
			<h2 id="courseHead">Students</h2>
			<table>
	            <thead>
	                <tr>
	                    <th>Sno.</th>
	                    <th>Name</th>
	                    <th>Marks Obained</th>
	                    <th>Result</th>
	                </tr>
	            </thead>
	            <tbody id="tableBody">
					
	            </tbody>
	        </table>
		</div>
	</div>
	
	
	
	
	<script src="js/TestAssignJS.js">
		
	</script>
</body>
</html>