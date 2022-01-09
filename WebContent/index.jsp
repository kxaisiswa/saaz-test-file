<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Student Login | SAAZ Student Management System</title>
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <link href="style.css" rel="stylesheet">
</head>
<body class="text-center bg-light">
	<main class="container">
        <div><!--image--></div>
        <div class="form-signin">
            <input type="button" value="Student"><input type="button" value="Staff" class="inactive" onclick="window.location.href='Admin_login.jsp'">
            <form action="LoginServlet" method="post">
                <h1 class="h3 mb-3 fw-bolder p-3">Sign in</h1>				
				<c:if test="${session_status != null}">
					<div class="alert alert-danger" role="alert">
						<c:out value="${session_status}"></c:out>
						<c:set var="session_status" value="${null}"></c:set>
					</div>
				</c:if>
                <input type="hidden" name="usertype" value="Student">
                <div class="form-floating">
                    <input type="text" name="userid" class="form-control" placeholder="ID number" required>
                    <label>ID number</label>
                </div>
                <div class="form-floating">
                    <input type="password" name="userpassword" class="form-control" placeholder="Password" required>
                    <label>Password</label>
                </div>
                <button class="w-100 btn btn-lg btn-primary mt-3" type="submit">Sign in</button>
                <p class="mt-5 mb-3 text-muted">&copy; 2017 - 2021</p>
            </form>
        </div>
    </main>
</body>
</html>