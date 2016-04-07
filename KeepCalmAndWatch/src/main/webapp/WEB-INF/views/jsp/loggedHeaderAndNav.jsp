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
		<a href="upload" class="btn btn-primary btn-sm" id="upload"
			type="submit">Качване</a>
	</div>
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
				href="profile">Моят канал</a></li>
			<li role="presentation"><a role="menuitem" tabindex="-1"
				href="#">Абонаменти</a></li>
			<li role="presentation" class="divider"></li>
			<li role="presentation"><a role="menuitem" tabindex="-1"
				href="${pageContext.request.contextPath}/logout">Изход</a></li>
		</ul>
	</div>
	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-3 sidenav">
				<br>
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="#section1">Начална страница</a></li>
					<li><a href="#section2">Моят канал</a></li>
					<li><a href="#section3">Абонаменти</a></li>
					<li><a href="#section3">История</a></li>
				</ul>
			</div>
			<p>Hello,${LoggedUser.channelName}</p>
			<table>
				<tr>
					<c:forEach var="video" items="${sessionScope.AllVideos}">
						<td><a
							href="${pageContext.request.contextPath}/watchVideo?v=${video.id}"><img
								src="data:image/gif;base64,${video.thumbnail}" width="50px"
								height="50px" /></a><br /> <a
							href="${pageContext.request.contextPath}/watchVideo?v=${video.id}"><c:out
									value="${video.title}" /></a><br /></td>
					</c:forEach>

				</tr>
			</table>
		</div>
	</div>
</body>
</html>