<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Регистрация на потребител</title>
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
<script type="text/javascript" src="js/validateRegistration.js"></script>
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

.input-group {
	width: 500px;
	position: absolute;
	top: 10px;
	left: 400px;
}
</style>
</head>
<body>

	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-3 sidenav">

				<h4>John's Blog</h4>
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="#section1">Начална страница</a></li>
					<li><a href="#section2">Моят канал</a></li>
					<li><a href="#section3">Абонаменти</a></li>
					<li><a href="#section3">История</a></li>
				</ul>
				
			</div>
			<div id="logo">
					<a href="/KeepCalmAndWatch"><img
						src="${pageContext.request.contextPath}/img/logo.jpg" width="50px"
						height="50px" /></a>
				</div>
				<div class="input-group">
					<input type="text" class="form-control" placeholder="Search Blog..">
					<span class="input-group-btn">
						<button class="btn btn-default" type="button">
							<span class="glyphicon glyphicon-search"></span>
						</button>
					</span>
				</div>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<br>
			<div class="row centered-form">
				<div
					class="col-xs-9 col-sm-9 col-md-4 col-sm-offset-3 col-md-offset-13">
					<div class="panel panel-default">
						<div class="panel-heading">
						<p style="color: red">${fail}</p>
							<h3 class="panel-title">Влизане</h3>
						</div>
						<div class="panel-body">
							<form:form method="POST" action="/KeepCalmAndWatch/login"
								role="form">
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group">
											<form:input path="username" type="text" name="username"
												id="username" class="form-control input-sm"
												placeholder="Потребителско име" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group">
											<form:input path="password" type="password" name="password"
												id="password" class="form-control input-sm"
												placeholder="Парола" />
										</div>
									</div>
								</div>

								<input type="submit" value="Влизане"
									class="btn btn-primary btn-block">

							</form:form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<footer class="container-fluid">
	<p>Footer Text</p>
	</footer>
</body>
</html>