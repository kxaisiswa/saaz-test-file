<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	
	if(session.getAttribute("session_id") == null) {
		response.sendRedirect("Admin_login.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:out value="${session_id}"/>
	<a href="AdminRedirectServlet?action=student">student</a>
	<a href="AdminRedirectServlet?action=program">program</a>
	<a href="AdminRedirectServlet?action=class">class</a>
	<a href="AdminRedirectServlet?action=subject">subject</a>
	<a href="AdminRedirectServlet?action=classsubject">class & subject</a>
	<a href="AdminRedirectServlet?action=admin">add admin</a>
	<a href="AdminRedirectServlet?action=account">account</a>
	<a href="LogoutServlet">logout</a>
</body>
</html>