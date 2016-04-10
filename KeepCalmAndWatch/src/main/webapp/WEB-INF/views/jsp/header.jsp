<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="forms" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
<script type="text/javascript" src="js/validateRegistration.js"></script>
<link href="${pageContext.request.contextPath}/css/headerAndSidenav.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/channel.css"
	rel="stylesheet">
</head>
<script type="application/x-javascript">
	
	
	
	
	
	addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 





</script>
<!-- bootstrap -->
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css'
	media="all" />
<!-- //bootstrap -->
<link href="css/dashboard.css" rel="stylesheet">
<!-- Custom Theme files -->
<link href="css/style.css" rel='stylesheet' type='text/css' media="all" />
<script src="js/jquery-1.11.1.min.js"></script>
<!--start-smoth-scrolling-->
<!-- fonts -->
<link
	href='//fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Poiret+One'
	rel='stylesheet' type='text/css'>
<!-- //fonts -->
<body>

	<div class="container-fluid">
		<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.html"><h1>
					<img src="img/logo_blue.jpg" height="65px" alt="" />
				</h1></a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<div class="top-search">
				<form class="navbar-form navbar-right">
					<input type="text" class="form-control" placeholder="Търсене...">
					<input type="submit" value=" ">
				</form>
			</div>

			<p style="color: red">${fail}</p>

			<div class="header-top-right">
				<c:if test="${not empty LoggedUser}">
					<div class="file">
						<a href="upload">Качване</a>
					</div>
				</c:if>
				<div class="signin">
					<a href="#small-dialog2" class="play-icon popup-with-zoom-anim">Регистрация</a>
					<!-- pop-up-box -->
					<script type="text/javascript" src="js/modernizr.custom.min.js"></script>
					<link href="css/popuo-box.css" rel="stylesheet" type="text/css"
						media="all" />
					<script src="js/jquery.magnific-popup.js" type="text/javascript"></script>
					<!--//pop-up-box -->
					<div id="small-dialog2" class="mfp-hide">
						<h3>Нов потребител</h3>
						<div class="signup">
							<form name="regform" onsubmit="return validateForm()"
								method="POST" action="/KeepCalmAndWatch/registerUser">
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group">
											<input name="username" id="username"
												class="form-control input-sm"
												placeholder="Потребителско име" />
										</div>
									</div>
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group">
											<input name="email" id="email" class="form-control input-sm"
												placeholder="Имейл адрес" required="required"
												pattern="([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?"
												title="Невалиден имейл адрес" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6 col-sm-12 col-md-12">
										<div class="form-group">
											<input name="channelName" id="channelName"
												class="form-control input-sm" placeholder="Име на канала" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group">
											<input type="password" name="password" id="password"
												class="form-control input-sm" placeholder="Парола" />
										</div>
									</div>
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group">
											<input type="password" name="passwordconfirmation"
												id="password_confirmation" class="form-control input-sm"
												placeholder="Потвърдете паролата">
										</div>
									</div>
								</div>
								<input type="submit" value="Регистрация"
									class="btn btn-info btn-block">
							</form>
						</div>
						<div class="clearfix"></div>
					</div>
					<script>
						$(document).ready(function() {
							$('.popup-with-zoom-anim').magnificPopup({
								type : 'inline',
								fixedContentPos : false,
								fixedBgPos : true,
								overflowY : 'auto',
								closeBtnInside : true,
								preloader : false,
								midClick : true,
								removalDelay : 300,
								mainClass : 'my-mfp-zoom-in'
							});

						});
					</script>
				</div>
				<div class="signin">
					<a href="#small-dialog" class="play-icon popup-with-zoom-anim">Вход</a>
					<div id="small-dialog" class="mfp-hide">
						<h3>Вход</h3>

						<div class="signup">
							<form name="loginForm" onsubmit="return validateLogin()"
								method="POST" action="/KeepCalmAndWatch/login">
								<input name="username" id="username" class="form-control"
									placeholder="Потребителско име" required="required" /> <br>
								<input name="password" id="password" type="password"
									placeholder="Парола" required="required" pattern=".{6,}"
									title="Minimum 6 characters required" autocomplete="off"
									class="form-control" /> <input type="submit" value="Вход" />
							</form>
							<div class="forgot">
								<a href="#">Забравена парола?</a>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="clearfix"></div>
		</nav>
	</div>

</body>
</html>