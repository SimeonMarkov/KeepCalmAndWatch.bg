<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/css/headerAndSidenav.css"
	rel="stylesheet">
</head>
<body>
	<div id="logo">
		<a href="/KeepCalmAndWatch"><img
			src="${pageContext.request.contextPath}/img/logo.jpg" width="200px"
			height="70px" /></a>
	</div>

	<div class="navbar-collapse collapse" id="navbar-collapsible">
		<form action="search" method="get">
			<div class="form-group" style="display: inline;">
				<div class="input-group">
					<div class="input-group-btn">
						<select name="category" class="btn btn-info dropdown-toggle"
							data-toggle="dropdown">
							<span class="glyphicon glyphicon-chevron-down"></span>
						<option value="videos">Клипове</option>
							<option value="users">Потребители</option>
						</select>
					</div>
					<input type="text" class="form-control"
						placeholder="What are searching for?" name="searchBar"> <span
						class="input-group-addon"><span
						class="glyphicon glyphicon-search"></span> </span>
				</div>
			</div>
		</form>
	</div>
	<div id="upload">
		<a href="register" class="btn btn-primary btn-sm" id="register"
			type="submit">Регистрация</a> <a href="login"
			class="btn btn-primary btn-sm" id="login" type="submit">Вход</a>
	</div>
	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-3 sidenav">
				<br>
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="#section1">Начална страница</a></li>
					<li><a href="#section2">Най-популярни</a></li>

				</ul>
			</div>
		</div>
	</div>
	<div class="navbar-collapse collapse" id="navbar-collapsible">
	</div>

</body>
</html>