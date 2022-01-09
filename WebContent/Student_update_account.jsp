<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	response.setHeader("Cache-Control","no-cache");
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader ("Expires", 0);
	
	if(session.getAttribute("session_id") == null) {
		response.sendRedirect("index.jsp");
	}
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <link href="sidebars.css" rel="stylesheet">
    <title>Student | SAAZ Student Management System</title>
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
        <h1 class="py-2 align-items-center fw-bold text-dark fs-4">Student</h1>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto">
            <li class="nav-item">
                <a href="StudentRedirectServlet?action=info" class="nav-link text-dark">Information</a>
            </li>
            <li>
                <a href="StudentRedirectServlet?action=parent" class="nav-link text-dark">Parent</a>
            </li>
            <li>
                <a href="StudentRedirectServlet?action=academic" class="nav-link text-dark">Academic</a>
            </li>
            <li>
                <a href="StudentRedirectServlet?action=academicinfo" class="nav-link text-dark">Academic Information</a>
            </li>
            <li>
                <a href="StudentRedirectServlet?action=account" class="nav-link active">Account</a>
            </li>
            <li>
				<a href="" data-bs-toggle="modal" data-bs-target="#exampleModal" class="nav-link text-dark">Logout</a>
            </li>
        </ul>
    </div>
    <div class="content">
        <button type="button" class="btn m-2" style="background-color: #e6e6e6">
            <img src="assets/img/arrow-left-short.svg" width="32" height="32"/>
        </button>
        <div class="col-md-7 col-lg-8 container" style="max-width: 900px;">
            <h4 class="mb-3">Maklumat Akaun</h4>
            <form action="UpdateStudentAccountServlet" method="post" class="needs-validation" novalidate>
                <div class="row g-3">
                	<c:if test="${status != null}">
                		<c:choose>
							<c:when test="${status == 'Account information updated'}">
								<div class="alert alert-success text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:when>
							<c:otherwise>
								<div class="alert alert-danger text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:otherwise>
						</c:choose>
					</c:if>
                    <div class="col-12">
                        <label class="form-label">Nama</label>
                        <input type="text" class="form-control" name="studname" placeholder="" value="<c:out value="${studentinfo.studname}" />" required>
                        <div class="invalid-feedback">
                            Sila isi nama dengan betul.
                        </div>
                    </div>
                    <div class="col-6">
                        <label class="form-label">No. Matrik</label>
                        <input type="text" class="form-control" name="studid" placeholder="" value="<c:out value="${studentinfo.studid}" />" readonly>
                        <div class="invalid-feedback">
                            Sila isi no. matrik dengan betul.
                        </div>
                    </div>
                    <div class="col-6">
                        <label class="form-label">Kata Laluan</label>
                        <input type="text" class="form-control" name="studpassword" placeholder="" value="<c:out value="${studentinfo.studpassword}" />" required>
                        <div class="invalid-feedback">
                            Sila isi kata laluan dengan betul.
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