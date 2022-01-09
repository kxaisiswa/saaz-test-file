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
		window.onload = function() {
			var display_studid = '${session_id}';
			
			var request = new XMLHttpRequest();
			request.open("GET", "https://saaz-microservice.herokuapp.com/display_value?studid=" + display_studid, true);
			request.onload = function() {
				var stud_object = JSON.parse(this.response);
				for (var i = 0; i < stud_object.length; i++) {
					document.getElementById("studid").innerHTML = stud_object[i].studID;
					document.getElementById("studic").innerHTML = stud_object[i].studIC;
					document.getElementById("studname").innerHTML = stud_object[i].studName;
					if (typeof stud_object[i].progName != 'undefined') {
						document.getElementById("studprog").innerHTML = stud_object[i].progName;
					}
					else {
						document.getElementById("studprog").innerHTML = "";
					}
					if (typeof stud_object[i].className != 'undefined') {
						document.getElementById("studclass").innerHTML = stud_object[i].className;
					}
					else {
						document.getElementById("studclass").innerHTML = "";
					}
				}
			}
			request.send();
		}
		
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
                <a href="StudentRedirectServlet?action=academic" class="nav-link active">Academic</a>
            </li>
            <li>
                <a href="StudentRedirectServlet?action=academicinfo" class="nav-link text-dark">Academic Information</a>
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
            <h4 class="mb-3">Pendaftaran Akademik</h4>
            <form action="UpdateStudentAcademicServlet" method="post" class="needs-validation" novalidate>
                <div class="row g-3">
                	<c:if test="${status != null}">
                		<c:choose>
							<c:when test="${status == 'Program & class information updated'}">
								<div class="alert alert-success text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:when>
							<c:otherwise>
								<div class="alert alert-danger text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:otherwise>
						</c:choose>
					</c:if>
                    <div class="col-6">
                        <label class="form-label">Program</label>
                        <select class="form-select" name="progcode" onchange="progvalue(this.value)" required>
                            <option value=""></option>
							<c:forEach items="${programlist}" var="pl">
								<option value="<c:out value="${pl.progcode}" />"><c:out value="${pl.progname}" /></option>
							</c:forEach>
                        </select>
                        <div class="invalid-feedback">
                            Sila pilih program.
                        </div>
                    </div>
                    <div class="col-6">
                        <label class="form-label">Kelas</label>
                        <select class="form-select" id="selectclass" name="classid" required>
                            <option value=""></option>
                        </select>
                        <div class="invalid-feedback">
                            Sila pilih kelas.
                        </div>
                    </div>
                    <hr class="my-5">
                    <button class="w-100 btn btn-primary btn-lg mb-5" type="submit">Submit</button>
                </div>
            </form>
            <h4 class="mb-3">Maklumat Pelajar</h4>
            <table>
                <tr>
                    <th>No. Matrik</th>
                    <td id="studid"></td>
                </tr>
                <tr>
                    <th>No. Kad Pengenalan</th>
                    <td id="studic"></td>
                </tr>
                <tr>
                    <th>Nama</th>
                    <td id="studname"></td>
                </tr>
                <tr>
                    <th>Program</th>
                    <td id="studprog"></td>
                </tr>
                <tr>
                    <th>Kelas</th>
                    <td id="studclass"></td>
                </tr>
            </table>
            <h4 class="mt-5 mb-3">Maklumat Subjek</h4>
            <ul class="list-group mb-5">
            	<c:forEach items="${subjectlist}" var="sl">
					<li class="list-group-item"><c:out value="${sl.subject.subjcode}" /> - <c:out value="${sl.subject.subjname}" /></li>
				</c:forEach>
            </ul>
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