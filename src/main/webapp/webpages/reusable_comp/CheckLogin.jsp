<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("user")==null){
		out.write("user not loggedin");
		response.sendRedirect("LoginPage.jsp");
	}
%>