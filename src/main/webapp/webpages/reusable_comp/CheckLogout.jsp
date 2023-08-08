<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("user")!=null){
		response.sendRedirect("Home.jsp");
	}else if(session.getAttribute("admin")!=null){
		response.sendRedirect("AdminPage.jsp");
	}
%>