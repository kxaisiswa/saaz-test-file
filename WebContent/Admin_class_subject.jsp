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
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Staff | SAAZ Student Management System</title>
  <link href="assets/css/bootstrap.css" rel="stylesheet">
  <link href="sidebars.css" rel="stylesheet">
  <style>
      table {
          background-color: #fff !important;
      }
      
      th {
          background-color: #e6e6e6 !important;
      }
  </style>
</head>
<body class="bg-light">
    <div class="p-3 text-dark sidebar-style">
        <h1 class="py-2 d-flex align-items-center fw-bold text-dark fs-4">Staff</h1>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto">
            <li class="nav-item">
                <a href="AdminRedirectServlet?action=student" class="nav-link text-dark">Student</a>
            </li>
            <li>
                <a href="AdminRedirectServlet?action=program" class="nav-link text-dark">Program</a>
            </li>
            <li>
                <a href="AdminRedirectServlet?action=class" class="nav-link text-dark">Class</a>
            </li>
            <li>
                <a href="AdminRedirectServlet?action=subject" class="nav-link text-dark">Subject</a>
            </li>
            <li>
                <a href="AdminRedirectServlet?action=classsubject" class="nav-link active">Assign Subject</a>
            </li>
        	<c:if test="${session_level == 'Superadmin'}">
	            <li>
	                <a href="AdminRedirectServlet?action=admin" class="nav-link text-dark">Staff</a>
	            </li>
	        </c:if>
            <li>
                <a href="AdminRedirectServlet?action=account" class="nav-link text-dark">Account</a>
            </li>
            <li>
                <a href="AdminRedirectServlet?action=statistic" class="nav-link text-dark">Statistic</a>
            </li>
            <li>
				<a href="" data-bs-toggle="modal" data-bs-target="#exampleModal" class="nav-link text-dark">Logout</a>
            </li>
        </ul>
    </div>
    <div class="content">
        <button type="button" class="btn m-2" style="background-color: #e6e6e6">
            <img src="assets/img/arrow-left-short.svg" width="32" height="32" />
        </button>
        <div class="col-md-7 col-lg-8 container" style="max-width: 900px;">
            <h4 class="mb-3">Mata Pelajaran</h4>
            <form action="AddClassSubjectServlet" method="post" class="needs-validation" novalidate>
                <div class="row g-3">
                	<c:if test="${status != null}">
                		<c:choose>
							<c:when test="${status == 'Subject assigned to class'}">
								<div class="alert alert-success text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:when>
							<c:otherwise>
								<div class="alert alert-danger text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:otherwise>
						</c:choose>
					</c:if>
                    <div class="col-4"></div>
                    <div class="col-4">
                        <label class="form-label">Kelas</label>
                        <select class="form-select" name="classid" required>
                            <option value=""></option>
                            <c:forEach items="${classlist}" var="cl">
								<option value="<c:out value="${cl.classid}" />"><c:out value="${cl.classname}" /></option>
							</c:forEach>
                        </select>
                        <div class="invalid-feedback">
                            Sila pilih kelas.
                        </div>
                    </div>
                    <div class="col-4"></div>
                    <div class="col-12">
                        <ul class="list-group">
                       		<c:forEach items="${subjectlist}" var="sl">
	                            <li class="list-group-item">
	                                <input class="form-check-input me-1" type="checkbox" value="<c:out value="${sl.subjcode}" />" <c:if test="${sl.subjcode == 'MUET1' || sl.subjcode == 'PA001'}"><c:out value="checked required" /></c:if> name="subjectlist"> <c:out value="${sl.subjcode}" /> - <c:out value="${sl.subjname}" />
	                            </li>
							</c:forEach>
                        </ul>
                    </div>
                    <button class="w-100 btn btn-primary btn-lg my-5" type="submit">Submit</button>
                </div>
            </form>
        </div>
    </div>
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel">
    	<div class="modal-dialog modal-sm">
    		<div class="modal-content">
    			<div class="modal-header">
    				<h5 class="modal-title" id="exampleModalLabel">Logout</h5>
    				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    			</div>
    			<div class="modal-body d-flex justify-content-center">
    				<button type="button" class="btn btn-secondary mx-1" data-bs-dismiss="modal">Cancel</button>
    				<button onclick="window.location.href='LogoutServlet'" type="button" class="btn btn-primary mx-1">Confirm</button>
    			</div>
    		</div>
  		</div>
	</div>
    <script src="assets/js/bootstrap.js"></script>
    <script src="form-validation.js"></script>
</body>
</html>