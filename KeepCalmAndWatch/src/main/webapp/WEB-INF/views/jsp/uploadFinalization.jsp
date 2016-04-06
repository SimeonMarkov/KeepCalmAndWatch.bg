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

	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-3 sidenav">

				<a href="#"> <span class="glyphicon glyphicon-align-justify"></span>
				</a>
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="#section1">Начална страница</a></li>
					<li><a href="#section2">Моят канал</a></li>
					<li><a href="#section3">Абонаменти</a></li>
					<li><a href="#section3">История</a></li>
				</ul>

			</div>
			<a href="/KeepCalmAndWatch"><img
				src="${pageContext.request.contextPath}/img/logo.jpg"
				width="50px" height="50px" /></a>
			<div class="navbar-collapse collapse" id="navbar-collapsible">
				<form action="search" method="post">
					<div class="form-group" style="display: inline;">
						<div class="input-group">
							<div class="input-group-btn">
								<select class="btn btn-info dropdown-toggle"
									data-toggle="dropdown">
									<span class="glyphicon glyphicon-chevron-down"></span>
									<option value="videos">Клипове</option>
									<option value="users">Потребители</option>
								</select>
							</div>
							<input type="text" class="form-control"
								placeholder="What are searching for?"> <span
								class="input-group-addon"><span
								class="glyphicon glyphicon-search"></span> </span>
						</div>
					</div>
				</form>
			</div>

			<a href="upload" class="btn btn-primary btn-sm" id="upload"
				type="submit">Качване</a>
			<div id="avatar">
				<img class="img-circle" alt="Cinque Terre" width="50" height="50"
					src="data:image/gif;base64,${LoggedUser.avatar}" />
			</div>
			<div class="dropdown">
				<button class="btn btn-default dropdown-toggle" type="button"
					id="menu1" data-toggle="dropdown">
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
					<li role="presentation"><a role="menuitem" tabindex="-1"
						href="#">Начална страница</a></li>
					<li role="presentation"><a role="menuitem" tabindex="-1"
						href="#">Моят канал</a></li>
					<li role="presentation"><a role="menuitem" tabindex="-1"
						href="#">Абонаменти</a></li>
					<li role="presentation" class="divider"></li>
					<li role="presentation"><a role="menuitem" tabindex="-1"
						href="${pageContext.request.contextPath}/logout">Изход</a></li>
				</ul>
			</div>
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Search Blog..">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">
						<span class="glyphicon glyphicon-search"></span>
					</button>
				</span>
			</div>
			<div>
				<p>${message}</p>
				<br>
			</div>
		</div>
	</div>
</body>
</html>