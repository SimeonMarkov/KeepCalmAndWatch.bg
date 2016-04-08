<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Канала на ${channel.username }</title>
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
<script type="text/javascript" src="js/channel.js"></script>
<link href="${pageContext.request.contextPath}/css/.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/channel.css"
	rel="stylesheet">
</head>
<body>

	<div class="container-fluid">

		<div class="row content">
			<div class="col-md-2 col-lg-2 sidenav">
				<div id="logo">
					<a href="/KeepCalmAndWatch"><img
						src="${pageContext.request.contextPath}/img/logo.jpg"
						width="200px" height="70px" /></a>
				</div>
			</div>

			<div class="col-md-1 col-lg-1 "></div>
			<div class="col-md-5 col-lg-5 ">
				<br>
				<div class="navbar-collapse collapse" id="navbar-collapsible">
					<form action="search" method="get">
						<div class="form-group" style="display: inline">
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
									placeholder="What are searching for?" name="searchBar">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-search"></span> </span>
							</div>
						</div>
					</form>
				</div>
			</div>
			<div class="col-md-2 col-lg-2 "></div>
			<div class="col-md-1 col-lg-1 ">
				<br>
				<div id="upload">
					<a href="upload" class="btn btn-primary btn-sm" id="upload"
						type="submit">Качване</a>
				</div>
			</div>
			<div class="col-md-1 col-lg-1 ">

				<div id="img_container">
				<c:if test="${not empty LoggedUser}">
					<div id="avatar">
						<img class="img-circle" alt="" width="50" height="51"
							src="data:image/gif;base64,${LoggedUser.avatar}" />
					</div>
				</c:if>

					<div class="dropdown">
						<button class="btn btn-default 	" type="button"
							data-toggle="dropdown">
							<br> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu dropdown-menu-right" role="menu"
							aria-labelledby="menu1">
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="index">Начална страница</a></li>
							<c:if test="${not empty LoggedUser}">
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="profile">Моят профил</a></li>
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="profile">Моят канал</a></li>
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="#">Абонаменти</a></li>
							</c:if>
							<li role="presentation" class="divider"></li>
							<li role="presentation"><a role="menuitem" tabindex="-1"
								href="${pageContext.request.contextPath}/logout">Изход</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>

		<div class="col-md-2 col-lg-2 sidenav">
			<ul class="nav nav-pills nav-stacked">
				<li class="active"><a href="#section1">Начална страница</a></li>
				<li><a href="#section2">Моят канал</a></li>
				<li><a href="#section3">Абонаменти</a></li>
				<li><a href="#section3">История</a></li>
			</ul>
		</div>
		<div class="col-md-1 col-lg-1"></div>
		<div class="col-lg-8 col-md-8">
			<div class="card hovercard">
				<div class="card-background">
					<img class="card-bkimg" alt="" src="">

				</div>
				<div class="useravatar">
					<img alt=""
						src="data:image/gif;base64,${ChosenUser.avatar}">
				</div>

			</div>
			<div class="btn-pref btn-group btn-group-justified btn-group-lg"
				role="group" aria-label="...">
				<div class="btn-group" role="group">
					<button type="button" id="stars" class="btn btn-primary"
						href="#tab1" data-toggle="tab">
						<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
						<div class="hidden-xs">Информация</div>
					</button>
				</div>
				<div class="btn-group" role="group">
					<button type="button" id="favorites" class="btn btn-default"
						href="#tab2" data-toggle="tab">
						<span class="glyphicon glyphicon-film" aria-hidden="true"></span>
						<div class="hidden-xs">Клипове</div>
					</button>
				</div>
				<div class="btn-group" role="group">
					<button type="button" id="following" class="btn btn-default"
						href="#tab3" data-toggle="tab">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<div class="hidden-xs">Плейлисти</div>
					</button>
				</div>
			</div>


			<div class="well">
				<div class="tab-content">
					<div class="tab-pane fade in active" id="tab1">
						<div class="card-info">
							<span class="card-title"><h1>${ChosenUser.channelName}</h1></span>
						</div>
						<div id="subscribe">
							<a href="subscribe" class="btn btn-primary btn-sm" id="subscribe"
								type="submit">Абониране</a>
						</div>

						<br>
						<h3>Описание</h3>
						<div class="panel panel-default">
							<div class="panel-body">${ChosenUser.description}</div>
						</div>
						<br>
						<div class="card-info">
							<span class="card-title">Регистриран на
								${ChosenUser.registrationDate}</span>
						</div>
					</div>
					<div class="tab-pane fade in" id="tab2">
						<ul class="list-unstyled video-list-thumbs row">
						<c:forEach var="video" items="${VideosForChannelName}">
							<li class="col-lg-3 col-sm-4 col-xs-6"><a href="${pageContext.request.contextPath}/watchVideo?v=${video.id}"
								title="${video.title}">
									<img src="data:image/gif;base64,${video.thumbnail}"
									alt="Barca" class="img-responsive" height="130px" />
									<h2>${video.title}</h2>
									<span class="glyphicon glyphicon-play-circle"></span> <span
									class="duration">03:15</span>
							</a></li>
						</c:forEach>
						</ul>
					</div>
				</div>
			</div>

		</div>
</body>
</html>