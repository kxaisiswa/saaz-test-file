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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <link href="sidebars.css" rel="stylesheet">
    <title>Student | SAAZ Student Management System</title>
    <style>
        table {
            width: 100%;
            background-color: #fff !important;
        }
        
        th {
            width: 220px;
            padding: 14px 25px;
        }
        
        td {
            padding: 14px 25px;
        }
    </style>
	<script>
		function progvalue(selected_value) {
			var select = document.getElementById("selectclass");
			while(select.options.length > 0) {
				select.remove(0);
			}
			
			<c:forEach items="${classlist}" var="cl">
				if (selected_value == "${cl.progcode}") {
					var option = document.createElement("option");
					option.value = "<c:out value="${cl.classid}" />";
					option.text = "<c:out value="${cl.classname}" />";
					select.add(option);
				}
			</c:forEach>
		}
	</script>
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
                <a href="StudentRedirectServlet?action=academicinfo" class="nav-link active">Academic Information</a>
            </li>
            <li>
                <a href="StudentRedirectServlet?action=account" class="nav-link text-dark">Account</a>
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
            <h4 class="mb-3">Maklumat Akademik</h4>
            
            <c:forEach items="${programlist}" var="pl">
            	<div class="card my-5">
            	<h5 class="card-header" style="background-color: #f2f2f2 !important;"><c:out value="${pl.progname}" /></h5>
	            	<div class="card-body px-4">
					<c:forEach items="${classlist}" var="cl">
						<c:if test="${pl.progcode == cl.progcode}">
							<div class="card w-100 my-4">
								<div class="card-body" style="background-color: #f2f2f2 !important;">
		                            <h6 class="card-title fw-bold"><c:out value="[${pl.progcode}] ${cl.classname}" /></h6>
		                            
		                            <c:forEach items="${classtotallist}" var="ctl">
										<c:if test="${cl.classid == ctl.classid}">
											<span class="badge bg-primary rounded-pill me-1"><c:out value="${ctl.totalstudent} / ${ctl.classcapacity}"></c:out></span>
											<c:choose>
												<c:when test="${ctl.totalstudent < ctl.classcapacity}">
													<span class="badge bg-success rounded-pill"><c:out value="Open"></c:out></span>
												</c:when>
												<c:otherwise>
													<span class="badge bg-danger rounded-pill"><c:out value="Close"></c:out></span>
												</c:otherwise>
											</c:choose>
										</c:if>
									</c:forEach>
		                        </div>
		                        <ul class="list-group list-group-flush">
		                        	<c:forEach items="${classsubjectlist}" var="csl">
										<c:if test="${cl.classid == csl.classid}">
											<li class="list-group-item"><c:out value="${csl.subject.subjcode}" /> - <c:out value="${csl.subject.subjname}" /></li>
										</c:if>
									</c:forEach>
		                        </ul>
							</div>
						</c:if>
					</c:forEach>
					</div>
				</div>
			</c:forEach>
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