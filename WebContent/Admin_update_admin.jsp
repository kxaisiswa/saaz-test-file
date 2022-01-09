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
                <a href="AdminRedirectServlet?action=classsubject" class="nav-link text-dark">Assign Subject</a>
            </li>
        	<c:if test="${session_level == 'Superadmin'}">
	            <li>
	                <a href="AdminRedirectServlet?action=admin" class="nav-link active">Staff</a>
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
        <button onclick="window.location.href='AdminRedirectServlet?action=admin'" type="button" class="btn m-2" style="background-color: #e6e6e6">
            <img src="assets/img/arrow-left-short.svg" width="32" height="32" />
        </button>
        <div class="col-md-7 col-lg-8 container" style="max-width: 900px;">
            <h4 class="mb-3">Maklumat Akaun</h4>
            <form action="UpdateAdminServlet" method="post" class="needs-validation" novalidate>
                <div class="row g-3">
                	<c:if test="${status != null}">
                		<c:choose>
							<c:when test="${status == 'Admin information updated'}">
								<div class="alert alert-success text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:when>
							<c:otherwise>
								<div class="alert alert-danger text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:otherwise>
						</c:choose>
					</c:if>
                    <div class="col-6">
                        <label class="form-label">ID Staf</label>
                        <input type="hidden" class="form-control" name="update_action" value="level">
                        <input type="text" class="form-control" name="adminid" value="<c:out value="${admininfo.adminid}" />" readonly>
                    </div>
                    <div class="col-6">
                        <label class="form-label">Nama</label>
                        <input type="text" class="form-control" name="adminname" value="<c:out value="${admininfo.adminname}" />" readonly>
                    </div>
                    <div class="col-6">
                        <label class="form-label">Kata Laluan</label>
                        <input type="text" class="form-control" name="adminpassword" value="<c:out value="${admininfo.adminpassword}" />" readonly>
                    </div>
                    <div class="col-6">
                        <label class="form-label">ID Penyelia</label>
                        <input type="text" class="form-control" name="superviseid" value="<c:out value="${admininfo.superviseid}" />" readonly>
                    </div>
                    <div class="col-4">
                        <label class="form-label">Email Staf</label>
                        <input type="email" class="form-control" name="adminemail" value="<c:out value="${admininfo.adminemail}" />" readonly>
                    </div>
                    <div class="col-4">
                        <label class="form-label">No. Telefon</label>
                        <input type="text" class="form-control" name="adminphonenum" value="<c:out value="${admininfo.adminphonenum}" />" readonly>
                    </div>
                    <div class="col-4">
                        <label class="form-label">Tahap</label>
                        <select class="form-select" name="adminlevel" required>
							<option value=""></option>
							<option <c:if test = "${admininfo.adminlevel == 'Superadmin'}"><c:out value="selected" /></c:if> value="Superadmin">Superadmin</option>
							<option <c:if test = "${admininfo.adminlevel == 'Normal Admin'}"><c:out value="selected" /></c:if> value="Normal Admin">Normal Admin</option>
						</select>
                        <div class="invalid-feedback">
                            Sila pilih tahap staf.
                        </div>
                    </div>
                    <hr class="my-5">
                    <button class="w-100 btn btn-primary btn-lg mb-5" type="submit">Submit</button>
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