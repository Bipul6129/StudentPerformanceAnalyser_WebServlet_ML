<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>SPS-Secondary</title>
	<%@ include file="reusable_comp/ImportHeaders.jsp" %>
	<style>
        .card{
            align-items: normal;
            width:40%
        }
        @media only screen and (max-width:1230px){
            .card{
                width: 400px;
            }
        }
    </style>
</head>
	

<body>  
	<%@ include file="reusable_comp/CheckLogout.jsp" %>
	<%@ include file="reusable_comp/NavBar.jsp" %>
    <div class="center_div">
        <div class="carpet" style="">
            <img src="assets/signinImg.jpg"/>
            <div class="card" style="">
                <h2>SPS Login</h2>
                <div class="gap_div"></div>
                <h5>Welcome to Login Page</h5>
                <div class="gap_div"></div>
                <div class="gap_div"></div>
                <form id="loginform" class="form_div" method="post">
                    <input type="text" placeholder="Enter username" name="username" id="username"/>
                    <div class="gap_div"></div>
                    <input type="password" placeholder="Enter password" name="password" id="password"/>
                    <div class="gap_div"></div>
                    <button type="submit" style="width: 70%;" id="loginbtn">LOGIN</button>
                </form>
                <div class="gap_div"></div>
                <h4>Need an Account?</h4>
            </div>
        </div>

    </div>
    
    <script src="js/loginUser.js">
    	
    </script>
    

    
</body>
</html>