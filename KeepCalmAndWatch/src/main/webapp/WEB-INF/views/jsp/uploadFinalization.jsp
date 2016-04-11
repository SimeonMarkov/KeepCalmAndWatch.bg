<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/img/favicon.ico"
	type="image/png" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/css/headerAndSidenav.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>
/* Set height of the grid so .sidenav can be 100% (adjust if needed) */
.row.content {
	height: 1500px
}

/* Set gray background color and 100% height */
.sidenav {
	background-color: #f1f1f1;
	height: 100%;
	width: 200px;
}

/* Set black background color, white text and some padding */
footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}

.form-control {
	resize: vertical;
}

a:hover {
	text-decoration: none;
}

.input-group {
	width: 500px;
	position: absolute;
	top: 5px;
	left: 400px;
}

#upload {
	position: absolute;
	top: 10px;
	right: 70px;
}

#avatar {
	position: absolute;
	top: 5px;
	right: 65px;
}

.dropdown {
	position: absolute;
	top: 5px;
	right: 25px;
}
</style>
</head>
<body>
	
	<%@include file="header.jsp"%>
	
	<br><br>
	
	<div class="container-fluid">
		<div class="row content">
			<div>
				<p style="font-size: 20px;">${message}</p>
				<br>
				<a href="${pageContext.request.contextPath}/index" style="text-decoration: none; font-size: 20px; color: green;">Back to home page</a>
			</div>
		</div>
	</div>
</body>
</html>