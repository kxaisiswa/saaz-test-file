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
<body>
    <div class="p-3 text-dark sidebar-style">
        <h1 class="py-2 d-flex align-items-center fw-bold text-dark fs-4">Staff</h1>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto">
            <li class="nav-item">
                <a href="AdminRedirectServlet?action=student" class="nav-link active">Student</a>
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
            <li>
                <a href="AdminRedirectServlet?action=admin" class="nav-link text-dark">Staff</a>
            </li>
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
        <button onclick="window.location.href='AdminRedirectServlet?action=student'" type="button" class="btn m-2" style="background-color: #e6e6e6">
            <img src="assets/img/arrow-left-short.svg" width="32" height="32" />
        </button>
        <div class="col-md-7 col-lg-8 container" style="max-width: 900px;">
            <h4 class="mb-3">Maklumat Pelajar</h4>
            <div class="row g-3">
                <div class="col-3">
                    <label class="form-label">No. Matrik</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studid}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">No. Kad Pengenalan</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studic}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Nama</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studname}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Semester</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studsemester}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Jantina</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studgender}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Bangsa</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studrace}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Agama</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studreligion}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Kewarganegaraan</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studnationality}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Tarikh Lahir</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studbirthdate}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">No. Sijil Lahir</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studbirthcertnum}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Email Pelajar</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studemail}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">No. Telefon</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studphonenum}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Alamat</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studaddress}"/></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Tempat Tinggal</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studlivingplace}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Kenderaan</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.transportmode}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Jarak Rumah</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studschooldistance}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Kod Kolej</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.collegecode}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Nama Kolej</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.collegename}" /></div>
                </div>
                <hr class="my-5">
                <h4 class="mb-3">Maklumat Akaun</h4>
                <div class="col-3">
                    <label class="form-label">No. Matrik</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studid}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Kata Laluan</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentinfo.studpassword}" /></div>
                </div>
                <hr class="my-5">
                <h4 class="mb-3">Maklumat Penjaga</h4>
                <div class="col-3">
                    <label class="form-label">Nama</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentparentinfo.parent.parentname}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">No. Kad Pengenalan</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentparentinfo.parent.parentic}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">No. Telefon</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentparentinfo.parent.parentphonenum}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Kewarganegaraan</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentparentinfo.parent.parentnationality}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Bangsa</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentparentinfo.parent.parentrace}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Agama</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentparentinfo.parent.parentreligion}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Hubungan</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentparentinfo.parent.parentrelation}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Bil. Tanggungan</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentparentinfo.parent.parentnumdependent}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Pekerjaan</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentparentinfo.parent.parentjob}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Gaji Bulanan</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentparentinfo.parent.parentincome}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">No. Cukai Pendapatan</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${studentparentinfo.parent.parenttaxnumber}" /></div>
                </div>
                <hr class="my-5">
                <h4 class="mb-3">Maklumat Akademik</h4>
                <div class="col-3">
                    <label class="form-label">Program</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${academicinfo.stuprogram.progname}" /></div>
                </div>
                <div class="col-3">
                    <label class="form-label">Kelas</label>
                </div>
                <div class="col-9">
                    <div class="fs-6 fw-bold"><c:out value="${academicinfo.stuclass.classname}" /></div>
                </div>
                <div class="col-6">
                    <label class="form-label">Subjek</label>
                </div>
                <ul class="list-group">
                	<c:forEach items="${subjectlist}" var="sl">
						<li class="list-group-item"><c:out value="${sl.subject.subjcode}" /> - <c:out value="${sl.subject.subjname}" /></li>
					</c:forEach>
                </ul>
                <hr class="my-5">
            </div>
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
</body>
</html>