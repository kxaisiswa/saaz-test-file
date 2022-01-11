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
	<script>
		function displaycollege() {
			document.getElementById("collegeinput").style.display = "";
			document.getElementById("homeinput").style.display = "none";
		}
		function displayhome() {
			document.getElementById("collegeinput").style.display = "none";
			document.getElementById("homeinput").style.display = "";
		}
		
		window.onload = function() {
			if('${studentinfo.studlivingplace}' == "Kolej") {
				displaycollege();
			}
			else if('${studentinfo.studlivingplace}' == "Rumah") {
				displayhome();
			}
		}
		function displayinput(sendinput) {
			if(sendinput.value == "Kolej") {
				displaycollege();
			}
			else if(sendinput.value == "Rumah") {
				displayhome();
			}
			else {
				document.getElementById("collegeinput").style.display = "none";
				document.getElementById("homeinput").style.display = "none";
			}
		}
	</script>
</head>
<body class="bg-light">
	<div class="p-3 text-dark sidebar-style">
        <h1 class="py-2 align-items-center fw-bold text-dark fs-4">Student</h1>
        <hr>
        <ul class="nav nav-pills flex-column mb-auto">
            <li class="nav-item">
                <a href="StudentRedirectServlet?action=info" class="nav-link active">Information</a>
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
            <h4 class="mb-3">Dokumen Pelajar</h4>
            <table class="table">
                <tr>
                    <th>Bil.</th>
                    <th>Dokumen</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>Surat Tawaran Kemasukan Ke Tingakatan Enam Semester 1</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Salinan Keputusan SPM</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Salinan Kad Pengenalan Pelajar</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>Salinan Sijil Kelahiran Pelajar</td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>Salinan Sijil Berhenti Sekolah</td>
                </tr>
                <tr>
                    <td>6</td>
                    <td>Salinan Kad Pengenalan Bapa</td>
                </tr>
                <tr>
                    <td>7</td>
                    <td>Salinan Kad Pengenalan Ibu</td>
                </tr>
                <tr>
                    <td>8</td>
                    <td>Salinan Penyata Gaji Bapa</td>
                </tr>
                <tr>
                    <td>9</td>
                    <td>Salinan Penyata Gaji Ibu</td>
                </tr>
                <tr>
                    <td>10</td>
                    <td>Borang Pengakuan Pendapatan Bagi Ibu Bapa Yang Tiada Penyata Gaji</td>
                </tr>
                <tr>
                    <td>11</td>
                    <td>Bil Elektrik atau Air Dengan Alamat Rumah Terkini</td>
                </tr>
            </table>
            <h4 class="mb-3 mt-5">Maklumat Pelajar</h4>
            <form action="UpdateStudentServlet" method="post" enctype="multipart/form-data" class="needs-validation" novalidate>
                <div class="row g-3">
                	<c:if test="${status != null}">
                		<c:choose>
							<c:when test="${status == 'Student information updated'}">
								<div class="alert alert-success text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:when>
							<c:when test="${status == 'Student information updated & file uploaded'}">
								<div class="alert alert-success text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:when>
							<c:otherwise>
								<div class="alert alert-danger text-center" role="alert"><c:out value="${status}"></c:out></div>
							</c:otherwise>
						</c:choose>
					</c:if>
                    <div class="col-6">
                        <label class="form-label">No. Matrik</label>
                        <input type="text" class="form-control" name="studid" placeholder="" value="<c:out value="${studentinfo.studid}" />" readonly>
                        <div class="invalid-feedback">
                            Sila isi no. matrik dengan betul.
                        </div>
                    </div>
                    <div class="col-6">
                        <label class="form-label">No. Kad Pengenalan</label>
                        <input type="text" class="form-control" name="studic" placeholder="" value="<c:out value="${studentinfo.studic}" />" maxLength="12" required>
                        <div class="invalid-feedback">
                            Sila isi no. kad pengenalan dengan betul.
                        </div>
                    </div>
                    <div class="col-8">
                        <label class="form-label">Nama</label>
                        <input type="text" class="form-control" name="studname" placeholder="" value="<c:out value="${studentinfo.studname}" />" required>
                        <div class="invalid-feedback">
                            Sila isi nama pelajar dengan betul.
                        </div>
                    </div>
                    <div class="col-4">
                        <label class="form-label">Semester</label>
                        <select class="form-select" name="studsemester" required>
							<option></option>
							<option value="1" <c:if test = "${studentinfo.studsemester == 1}"><c:out value="selected" /></c:if> >1</option>
							<option value="2" <c:if test = "${studentinfo.studsemester == 2}"><c:out value="selected" /></c:if> >2</option>
							<option value="3" <c:if test = "${studentinfo.studsemester == 3}"><c:out value="selected" /></c:if> >3</option>
						</select>
                        <div class="invalid-feedback">
                            Sila pilih semester pelajar.
                        </div>
                    </div>
                    <div class="col-4">
                        <label class="form-label">Jantina</label>
                        <select class="form-select" name="studgender" required>
							<option></option>
							<option value="Lelaki" <c:if test = "${studentinfo.studgender == 'Lelaki'}"><c:out value="selected" /></c:if> >Lelaki</option>
							<option value="Perempuan" <c:if test = "${studentinfo.studgender == 'Perempuan'}"><c:out value="selected" /></c:if> >Perempuan</option>
						</select>
                        <div class="invalid-feedback">
                            Sila pilih jantina pelajar.
                        </div>
                    </div>
                    <div class="col-4">
                        <label class="form-label">Bangsa</label>
                        <select class="form-select" name="studrace" required>
							<option></option>
							<option value="Melayu" <c:if test = "${studentinfo.studrace == 'Melayu'}"><c:out value="selected" /></c:if> >Melayu</option>
							<option value="Cina" <c:if test = "${studentinfo.studrace == 'Cina'}"><c:out value="selected" /></c:if> >Cina</option>
							<option value="India" <c:if test = "${studentinfo.studrace == 'India'}"><c:out value="selected" /></c:if> >India</option>
						</select>
                        <div class="invalid-feedback">
                            Sila pilih bangsa pelajar.
                        </div>
                    </div>
                    <div class="col-4">
                        <label class="form-label">Agama</label>
                        <select class="form-select" name="studreligion" required>
							<option></option>
							<option value="Islam" <c:if test = "${studentinfo.studreligion == 'Islam'}"><c:out value="selected" /></c:if> >Islam</option>
							<option value="Buddha" <c:if test = "${studentinfo.studreligion == 'Buddha'}"><c:out value="selected" /></c:if> >Buddha</option>
							<option value="Hindu" <c:if test = "${studentinfo.studreligion == 'Hindu'}"><c:out value="selected" /></c:if> >Hindu</option>
							<option value="Kristian" <c:if test = "${studentinfo.studreligion == 'Kristian'}"><c:out value="selected" /></c:if> >Kristian</option>
						</select>
                        <div class="invalid-feedback">
                            Sila pilih agama pelajar.
                        </div>
                    </div>
                    <div class="col-4">
                        <label class="form-label">Kewarganegaraan</label>
                        <select class="form-select" name="studnationality" required>
							<option></option>
							<option value="Warganegara" <c:if test = "${studentinfo.studnationality == 'Warganegara'}"><c:out value="selected" /></c:if> >Warganegara</option>
							<option value="Bukan Warganegara" <c:if test = "${studentinfo.studnationality == 'Bukan Warganegara'}"><c:out value="selected" /></c:if> >Bukan Warganegara</option>
							<option value="Penduduk Tetap" <c:if test = "${studentinfo.studnationality == 'Penduduk Tetap'}"><c:out value="selected" /></c:if> >Penduduk Tetap</option>
						</select>
                        <div class="invalid-feedback">
                            Sila pilih kewarganegaraan pelajar.
                        </div>
                    </div>
                    <div class="col-4">
                        <label class="form-label">Tarikh Lahir</label>
                        <input type="text" class="form-control" name="studbirthdate" placeholder="" value="<c:out value="${studentinfo.studbirthdate}" />" required onfocus="(this.type='date')">
                        <div class="invalid-feedback">
                            Sila isi tarikh lahir dengan betul.
                        </div>
                    </div>
                    <div class="col-4">
                        <label class="form-label">No. Sijil Lahir</label>
                        <input type="text" class="form-control" name="studbirthcertnum" placeholder="" value="<c:out value="${studentinfo.studbirthcertnum}" />" maxLength="10" required>
                        <div class="invalid-feedback">
                            Sila isi no. sijil lahir dengan betul.
                        </div>
                    </div>
                    <div class="col-6">
                        <label class="form-label">Email Pelajar</label>
                        <input type="email" class="form-control" name="studemail" placeholder="" value="<c:out value="${studentinfo.studemail}" />" readonly>
                        <div class="invalid-feedback">
                            Sila isi email pelajar dengan betul.
                        </div>
                    </div>
                    <div class="col-6">
                        <label class="form-label">No. Telefon</label>
                        <input type="text" class="form-control" name="studphonenum" placeholder="" value="<c:out value="${studentinfo.studphonenum}" />" maxLength="12" required>
                        <div class="invalid-feedback">
                            Sila isi no. telefon dengan betul.
                        </div>
                    </div>
                    <div class="col-12">
                        <label class="form-label">Alamat</label>
                        <textarea name="studaddress" class="form-control" style="resize: none;" rows="5" required><c:out value="${studentinfo.studaddress}"/></textarea>
                        <div class="invalid-feedback">
                            Sila masukkan alamat dengan betul.
                        </div>
                    </div>
                    <div class="col-6">
                        <label class="form-label">Dokumen</label>
                        <input type="file" name="studfile" class="form-control">
                    </div>
                    <div class="col-6"></div>
                    <div class="col-4"></div>
                    <div class="col-4 my-4 text-center">
                    	<c:if test = "${studentinfo.studdocpath != null}">
							<input class="btn btn-secondary" type="button" value="Download" onclick="window.location.href='DownloadFileServlet?filename=<c:out value="${studentinfo.studdocpath}" />'"/>
						</c:if>
                    </div>
                    <div class="col-4"></div>
                    <div class="col-4"></div>
                    <div class="col-4">
                        <label class="form-label">Tempat Tinggal</label>
                        <select class="form-select" name="studlivingplace" onchange="displayinput(this)">
							<option value="none"></option>
							<option value="Kolej" <c:if test = "${studentinfo.studlivingplace == 'Kolej'}"><c:out value="selected" /></c:if> >Kolej</option>
							<option value="Rumah" <c:if test = "${studentinfo.studlivingplace == 'Rumah'}"><c:out value="selected" /></c:if> >Rumah</option>
						</select>
                        <div class="invalid-feedback">
                            Sila pilih tempat tinggal pelajar.
                        </div>
                    </div>
                    <div class="col-4"></div>
                    <div class="row mt-4" id="homeinput" style="display: none !important;">
	                    <div class="col-6">
	                        <label class="form-label">Kenderaan</label>
	                        <input type="text" class="form-control" name="transportmode" placeholder="" value="<c:out value="${studentinfo.transportmode}" />">
	                        <div class="invalid-feedback">
	                            Sila isi kenderaan dengan betul.
	                        </div>
	                    </div>
	                    <div class="col-6">
	                        <label class="form-label">Jarak Rumah</label>
	                        <input type="number" class="form-control" name="studschooldistance" placeholder="" value="<c:out value="${studentinfo.studschooldistance}" />">
	                        <div class="invalid-feedback">
	                            Sila isi jarak rumah dengan betul.
	                        </div>
	                    </div>
					</div>
	                <div class="row mt-4" id="collegeinput" style="display: none !important;">
	                	<div class="col-6">
	                        <label class="form-label">Kod Kolej</label>
	                        <select class="form-select" name="collegecode" required>
								<option></option>
								<option value="A" <c:if test = "${studentinfo.collegecode == 'A'}"><c:out value="selected" /></c:if> >A</option>
								<option value="B" <c:if test = "${studentinfo.collegecode == 'B'}"><c:out value="selected" /></c:if> >B</option>
							</select>
	                        <div class="invalid-feedback">
	                            Sila pilih kod kolej pelajar.
	                        </div>
	                    </div>
	                    <div class="col-6">
	                        <label class="form-label">Nama Kolej</label>
	                        <select class="form-select" name="collegename" required>
								<option></option>
								<option value="ASTRIK" <c:if test = "${studentinfo.collegename == 'ASTRIK'}"><c:out value="selected" /></c:if> >ASTRIK</option>
								<option value="BELIMA" <c:if test = "${studentinfo.collegename == 'BELIMA'}"><c:out value="selected" /></c:if> >BELIMA</option>
							</select>
	                        <div class="invalid-feedback">
	                            Sila pilih nama kolej pelajar.
	                        </div>
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