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
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	<script type="text/javascript">
		google.charts.load('current', {'packages':['bar']});
    	google.charts.setOnLoadCallback(drawClassChart);
    	google.charts.setOnLoadCallback(drawProgramChart);
    	google.charts.setOnLoadCallback(drawSemesterChart);
    	
    	function drawClassChart() {
    		var class_array = new Array();
    		
    		class_array[0] = ['Class', 'Total'];
    		<c:forEach begin="0" end="${studentclasslist.size()}" step="1" varStatus="loop" items="${studentclasslist}" var="scl">
    			class_array["${loop.count}"] = ["${scl.stuclass.classname}", +"${scl.totalbyclass}"];
    		</c:forEach>
    		
    		var data = new google.visualization.arrayToDataTable(class_array);
    		var options = {
    			legend: { position: 'none'},
    			bar: { groupWidth: '40%' },
    			colors: ['#dc3545']
    		};
    		
    		var chart = new google.charts.Bar(document.getElementById('class_chart'));
    		chart.draw(data, google.charts.Bar.convertOptions(options));
    	}
    	
    	function drawProgramChart() {
    		var program_array = new Array();
    		
    		program_array[0] = ['Program', 'Total'];
    		<c:forEach begin="0" end="${programtotal.size()}" step="1" varStatus="loop" items="${programtotal}" var="pt">
    			program_array["${loop.count}"] = ["${pt.progname}", +"${pt.totalbyprogram}"];
    		</c:forEach>
		
    		var data = new google.visualization.arrayToDataTable(program_array);
    		var options = {
    			legend: { position: 'none'},
    			bar: { groupWidth: '40%' },
    			colors: ['#6f42c1']
    		};
    		
    		var chart = new google.charts.Bar(document.getElementById('program_chart'));
    		chart.draw(data, google.charts.Bar.convertOptions(options));
    	}
    	
    	function drawSemesterChart() {
    		var semester_array = new Array();
    		
    		semester_array[0] = ['Semester', 'Total'];
    		<c:forEach begin="0" end="${semestertotal.size()}" step="1" varStatus="loop" items="${semestertotal}" var="st">
    			semester_array["${loop.count}"] = ["Semester ${st.studsemester}", +"${st.totalbysemester}"];
    		</c:forEach>
    		
    		var data = new google.visualization.arrayToDataTable(semester_array);
    		var options = {
    			legend: { position: 'none'},
    			bar: { groupWidth: '40%' },
    			colors: ['#d63384']
    		};
    		
    		var chart = new google.charts.Bar(document.getElementById('semester_chart'));
    		chart.draw(data, google.charts.Bar.convertOptions(options));
    	}
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
                <a href="AdminRedirectServlet?action=subject" class="nav-link text-dark">Subject</a>
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
                <a href="AdminRedirectServlet?action=statistic" class="nav-link active">Statistic</a>
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
            <h4 class="mb-3">Statistik Pelajar</h4>
            
			<div class="card mb-4">
				<div class="card-header">
					Class Graph
				</div>
				<div class="card-body p-5">
      				<div id="class_chart" style="width: 100%; height: 350px; margin: 0 auto; display: inline-block;"></div>
				</div>
			</div>
			
            <div class="card mb-4">
				<div class="card-header">
					Program Graph
				</div>
				<div class="card-body p-5">
      				<div id="program_chart" style="width: 100%; height: 350px; margin: 0 auto; display: inline-block;"></div>
				</div>
			</div>
			
			<div class="card mb-4">
				<div class="card-header">
					Semester Graph
				</div>
				<div class="card-body p-5">
      				<div id="semester_chart" style="width: 100%; height: 350px; margin: 0 auto; display: inline-block;"></div>
				</div>
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
    <script src="form-validation.js"></script>
</body>
</html>