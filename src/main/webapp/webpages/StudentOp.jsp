<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file = "reusable_comp/ImportHeaders.jsp" %>
<title>Insert title here</title>
</head>
<style>
	.addStudentImg{
		width:320px;
		height:320px;
	}
	.carpet{
		justify-content:space-evenly;
	}
	.card{
		width:auto;
		align-items:normal;
	}
	.center_div{
		margin-top:56px;
	}
</style>
<body>
	<%@ include file="reusable_comp/CheckLogin.jsp" %>
	<%@ include file="reusable_comp/NavBar.jsp" %>
	<div class="center_div">
        <div class="carpet" style="">
        	<div class="card">
        		<img class="addStudentImg" src="assets/AddStudent.svg"/>
        		<button class="successButton" onClick="gotoView()">Add Students</button>
        	</div>
            <div class="card">
            	<img class="addStudentImg" src="assets/seeUser.svg"/>
            	<button class="dangerButton">View Students</button>
            </div>
            
        </div>
    </div>
    
    <script>
    	function gotoView(){
    		window.location.href="AddStudent.jsp";
    	}
    </script>
</body>
</html>