<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	
	if(session.getAttribute("session_id") == null) {
		response.sendRedirect("Student_login.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:out value="${session_id}"/><br><br>
	<a href="StudentRedirectServlet?action=info">view & update</a><br>
	<a href="StudentRedirectServlet?action=parent">view & update parent</a><br>
	<a href="StudentRedirectServlet?action=academic">academic</a><br>
	<a href="StudentRedirectServlet?action=account">account</a><br>
	<a href="LogoutServlet">logout</a>
</body>
</html>