<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="sps_website.model.*"%>

<%
	if(session.getAttribute("user")==null){
		%>
	<header id="navbar">
        <div class="logo">
            <h1>LOGO HERE</h1>
        </div>
        <input type="checkbox" id="nav_check" hidden>
        <nav>
            <div class="logo">
                <h1>LOGO HERE</h1>
            </div>
            <ul>
                <li>
                    <a href="LoginPage.jsp" class="">Login</a>
                </li>
                <li>
                    <a href="RegisterPage.jsp">Register</a>
                </li>
                
            </ul>
        </nav>
        <label for="nav_check" class="hamburger">
            <div></div>
            <div></div>
            <div></div>
        </label>
 	</header>
	
	<%
		
	}else if(session.getAttribute("user")!=null){
		
		%>
	<header id="navbar">
        <div class="logo">
            <h1>LOGO HERE</h1>
        </div>
        <input type="checkbox" id="nav_check" hidden>
        <nav>
            <div class="logo">
                <h1>LOGO HERE</h1>
            </div>
            <ul>
            	<li>
                	<a href="AddCourse.jsp">My Course</a>
                </li>
                
                <li>
                	<a href="AddCollege.jsp">Colleges</a>
                </li>
                
                <li>
					<a href="StudentOp.jsp">Student</a>
				</li>
                <li>
                	<a href="TestView.jsp">Tests</a>
                </li>
                <li>
                    <a href="/sps_website/webpages/logoutuser" class="">Logout</a>
                </li>
				
				
            </ul>
        </nav>
        <label for="nav_check" class="hamburger">
            <div></div>
            <div></div>
            <div></div>
        </label>
 	</header>
		
		
		<%
	}
%>
