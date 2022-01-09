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
                <a href="StudentRedirectServlet?action=parent" class="nav-link active">Parent</a>
            </li>
            <li>
                <a href="StudentRedirectServlet?action=academic" class="nav-link text-dark">Academic</a>
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
            <h4 class="mb-3">Maklumat Penjaga</h4>
            <form action="AddParentServlet" method="post" class="needs-validation" novalidate>
                <div class="row g-3">
                	<c:if test="${status != null}">
                		<c:choose>
							<c:when test="${status == 'Parent information added'}">
								<div class="alert alert-success text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:when>
							<c:otherwise>
								<div class="alert alert-danger text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:otherwise>
						</c:choose>
					</c:if>
                    <input type="hidden" name="studid" value="<c:out value="${session_id}" />">
                    <div class="col-12">
                        <label class="form-label">Nama</label>
                        <input type="text" class="form-control" name="parentname" placeholder="" value="<c:out value="${studentinfo.parent.parentname}" />" required>
                        <div class="invalid-feedback">
                            Sila isi nama dengan betul.
                        </div>
                    </div>
                    <div class="col-6">
                        <label class="form-label">No. Kad Pengenalan</label>
                        <input type="text" class="form-control" name="parentic" placeholder="" value="<c:out value="${studentinfo.parent.parentic}" />" required>
                        <div class="invalid-feedback">
                            Sila isi no. kad pengenalan dengan betul.
                        </div>
                    </div>
                    <div class="col-6">
                        <label class="form-label">No. Telefon</label>
                        <input type="text" class="form-control" name="parentphonenum" placeholder="" value="<c:out value="${studentinfo.parent.parentphonenum}" />" required>
                        <div class="invalid-feedback">
                            Sila isi no. telefon dengan betul.
                        </div>
                    </div>
                    <div class="col-4">
                        <label class="form-label">Kewarganegaraan</label>
                        <select class="form-select" name="parentnationality" required>
							<option></option>
							<option value="Warganegara" <c:if test = "${studentinfo.parent.parentnationality == 'Warganegara'}"><c:out value="selected" /></c:if> >Warganegara</option>
							<option value="Bukan Warganegara" <c:if test = "${studentinfo.parent.parentnationality == 'Bukan Warganegara'}"><c:out value="selected" /></c:if> >Bukan Warganegara</option>
							<option value="Penduduk Tetap" <c:if test = "${studentinfo.parent.parentnationality == 'Penduduk Tetap'}"><c:out value="selected" /></c:if> >Penduduk Tetap</option>
						</select>
                        <div class="invalid-feedback">
                            Sila pilih kewarganegaraan penjaga.
                        </div>
                    </div>
                    <div class="col-4">
                        <label class="form-label">Bangsa</label>
                        <input type="text" class="form-control" name="parentrace" placeholder="" value="<c:out value="${studentinfo.parent.parentrace}" />" required>
                        <div class="invalid-feedback">
                            Sila isi bangsa dengan betul.
                        </div>
                    </div>
                    <div class="col-4">
                        <label class="form-label">Agama</label>
                        <input type="text" class="form-control" name="parentreligion" placeholder="" value="<c:out value="${studentinfo.parent.parentreligion}" />" required>
                        <div class="invalid-feedback">
                            Sila isi agama dengan betul.
                        </div>
                    </div>
                    <div class="col-6">
                        <label class="form-label">Hubungan</label>
                        <select class="form-select" name="parentrelation" required>
							<option></option>
							<option value="Ibu" <c:if test = "${studentinfo.parent.parentrelation == 'Ibu'}"><c:out value="selected" /></c:if> >Ibu</option>
							<option value="Bapa" <c:if test = "${studentinfo.parent.parentrelation == 'Bapa'}"><c:out value="selected" /></c:if> >Bapa</option>
							<option value="Penjaga" <c:if test = "${studentinfo.parent.parentrelation == 'Penjaga'}"><c:out value="selected" /></c:if> >Penjaga</option>
						</select>
                        <div class="invalid-feedback">
                            Sila pilih hubungan penjaga.
                        </div>
                    </div>
                    <div class="col-6">
                        <label for="lastName" class="form-label">Bil. Tanggungan</label>
                        <input type="number" class="form-control" name="parentnumdependent" placeholder="" value="<c:out value="${studentinfo.parent.parentnumdependent}" />" required>
                        <div class="invalid-feedback">
                            Sila isi bil. tanggungan dengan betul.
                        </div>
                    </div>
                    <div class="col-6">
                        <label class="form-label">Pekerjaan</label>
                        <input type="text" class="form-control" name="parentjob" placeholder="" value="<c:out value="${studentinfo.parent.parentjob}" />" required>
                        <div class="invalid-feedback">
                            Sila isi pekerjaan dengan betul.
                        </div>
                    </div>
                    <div class="col-3">
                        <label class="form-label">Gaji Bulanan</label>
                        <input type="text" class="form-control" name="parentincome" placeholder="" value="<c:out value="${studentinfo.parent.parentincome}" />" required>
                        <div class="invalid-feedback">
                            Sila isi gaji bulanan dengan betul.
                        </div>
                    </div>
                    <div class="col-3">
                        <label class="form-label">No. Cukai Pendapatan</label>
                        <input type="text" class="form-control" name="parenttaxnumber" placeholder="" value="<c:out value="${studentinfo.parent.parenttaxnumber}" />" required>
                        <div class="invalid-feedback">
                            Sila isi no. cukai pendapatan dengan betul.
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