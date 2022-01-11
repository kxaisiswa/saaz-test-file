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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.25/js/jquery.dataTables.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.25/css/jquery.dataTables.css">
    <style>
        table {
            background-color: #fff !important;
        }
        
        th {
            background-color: #e6e6e6 !important;
        }
    </style>
    <script>
        $(document).ready(function() {
            $('#table_id').DataTable();
        });
    </script>
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
                <a href="AdminRedirectServlet?action=subject" class="nav-link active">Subject</a>
            </li>
            <li>
                <a href="AdminRedirectServlet?action=classsubject" class="nav-link text-dark">Assign Subject</a>
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
        <button onclick="window.location.href='AdminRedirectServlet?action=subject'" type="button" class="btn m-2" style="background-color: #e6e6e6">
            <img src="assets/img/arrow-left-short.svg" width="32" height="32" />
        </button>
        <div class="col-md-7 col-lg-8 container" style="max-width: 900px;">
            <h4 class="mb-3">Maklumat Subjek</h4>
            <form action="AddSubjectServlet" method="post" class="needs-validation" novalidate>
                <div class="row g-3">
                	<c:if test="${status != null}">
                		<c:choose>
							<c:when test="${status == 'New subject added'}">
								<div class="alert alert-success text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:when>
							<c:when test="${status == 'Subject Deleted'}">
								<div class="alert alert-success text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:when>
							<c:otherwise>
								<div class="alert alert-danger text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:otherwise>
						</c:choose>
					</c:if>
                    <div class="col-3">
                        <label class="form-label">Kod Subjek</label>
                        <input type="text" class="form-control" name="subjcode" maxLength="5" required>
                        <div class="invalid-feedback">
                            Sila isi kod subjek dengan betul.
                        </div>
                    </div>
                    <div class="col-6">
                        <label class="form-label">Nama Subjek</label>
                        <input type="text" class="form-control" name="subjname" required>
                        <div class="invalid-feedback">
                            Sila isi nama subjek dengan betul.
                        </div>
                    </div>
                    <div class="col-3">
                        <label class="form-label">Jam Kredit</label>
                        <input type="number" class="form-control" name="subjcredit" min="1" max="4" required>
                        <div class="invalid-feedback">
                            Sila isi jam kredit antara 1 hingga 4.
                        </div>
                    </div>
                    <div class="col-12">
                        <label class="form-label">Syarat</label>
                        <textarea name="subjrequirement" class="form-control" style="resize: none;" rows="5" required></textarea>
                        <div class="invalid-feedback">
                            Sila isi syarat subjek dengan betul.
                        </div>
                    </div>
                    <hr class="my-5">
                    <button class="w-100 btn btn-primary btn-lg mb-5" type="submit">Submit</button>
                </div>
            </form>
        </div>
        <div class="m-5">
            <table id="table_id" class="display mb-4 pt-4 bg-light">
                <thead>
                    <tr>
                        <th>Kod Subjek</th>
                        <th>Nama Subjek</th>
                        <th>Jam Kredit</th>
                        <th>Syarat</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach items="${subjectlist}" var="sl">
	                    <tr>
	                        <td><c:out value="${sl.subjcode}" /></td>
	                        <td><c:out value="${sl.subjname}" /></td>
	                        <td><c:out value="${sl.subjcredit}" /></td>
	                        <td><c:out value="${sl.subjrequirement}" /></td>
	                        <td>
	                            <button class="btn btn-primary" onclick="window.location.href='AdminRedirectServlet?action=updatesubject&updateid=<c:out value="${sl.subjcode}" />'" >Update</button>
	                            <c:if test="${session_level == 'Superadmin'}">
	                            	<button class="btn btn-danger" onclick="window.location.href='AdminRedirectServlet?action=deletesubject&deleteid=<c:out value="${sl.subjcode}" />'" >Delete</button>
	                        	</c:if>
	                        </td>
	                    </tr>
					</c:forEach>
                </tbody>
            </table>
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