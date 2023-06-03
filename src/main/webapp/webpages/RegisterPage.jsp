<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="reusable_comp/ImportHeaders.jsp" %>
<title>Insert title here</title>
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
            <img src="assets/signup.jpg" style="height:480px;"/>
            <div class="card" style="">
                <h2>SPS Register</h2>
                <div class="gap_div"></div>
                <h5>Welcome to Register Page</h5>
                <div class="gap_div"></div>
                <div class="gap_div"></div>
                <form class="form_div" id="registerForm">
                    <input type="email" placeholder="Enter Email" name="email" id="email"/>
                    
                    <div class="gap_div"></div>
                    <input type="text" placeholder="Enter username" name="username" id="username"/>
                    <div class="gap_div"></div>
                    <input type="password" placeholder="Enter password" name="password" id="password"/>
                    <div class="gap_div"></div>
                    
                    <button type="submit" style="width: 70%;" id="formregisterbtn">SignUp</button>
                </form>
                <i class=" fa-solid fa-spinner fa-spin-pulse fa-2xl" id="spinicon"></i>
                <div class="gap_div"></div>
                <h4>Have an Account?</h4>
                
            </div>
        </div>
    </div>
    <script type="text/javascript" src="js/registerUser.js"></script>
</body>
</html>