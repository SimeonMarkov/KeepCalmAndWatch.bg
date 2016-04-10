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

<link href="${pageContext.request.contextPath}/css/headerAndSidenav.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/channel.css"
	rel="stylesheet">
</head>
<body>

<%@include file="header.jsp"%>
	
	<br><br>
	
	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-3 col-md-2 col-lg-2 sidenav">
				<br>
				<ul class="nav nav-pills nav-stacked">
					<li class="active"><a href="#section1">Начална страница</a></li>
					<li><a href="#section2">Моят канал</a></li>
					<li><a href="#section3">Абонаменти</a></li>
					<li><a href="#section3">История</a></li>
				</ul>
			</div>
			<p><h2>Здравейте, ${LoggedUser.channelName}!</h2></p>
			<div class="tab-pane fade in" id="tab2">
				<ul class="list-unstyled video-list-thumbs row">
					<c:forEach var="video" items="${sessionScope.AllVideos}">
						<li class="col-lg-3 col-sm-4 col-xs-6"><a
							href="${pageContext.request.contextPath}/watchVideo?v=${video.id}"
							title="${video.title}"> <img
								src="${video.thumbnail}" 
								class="img-responsive" width="auto" height="150px" />
								<h2>${video.title}</h2> <span
								class="glyphicon glyphicon-play-circle"></span> <span
								class="duration">03:15</span>
						</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>