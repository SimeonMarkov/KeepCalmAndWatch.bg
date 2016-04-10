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

<script type="text/javascript" src="js/validateRegistration.js"></script>
<link href="${pageContext.request.contextPath}/css/headerAndSidenav.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/channel.css"
	rel="stylesheet">
</head>

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

	<%@include file="header.jsp"%>

	<br>
	<br>

	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12 col-sm-4 col-xs-6">
				<div class="tab-pane fade in" id="tab2">
<!-- 					<ul class="list-unstyled video-list-thumbs row"> -->
<%-- 						<c:forEach var="video" items="${sessionScope.AllVideos}"> --%>
<!-- 							<li class="col-lg-3 col-sm-4 col-xs-6"><a -->
<%-- 								href="${pageContext.request.contextPath}/watchVideo?v=${video.id}" --%>
<%-- 								title="${video.title}"> <img src="${video.thumbnail}" --%>
<!-- 									height="130px" /> -->
<%-- 									<h2>${video.title}</h2> <span --%>
<!-- 									class="glyphicon glyphicon-play-circle"></span> <span -->
<!-- 									class="duration">03:15</span> -->
<!-- 							</a></li -->
<%-- 						</c:forEach> --%>
<!-- 					</ul> -->
				</div>

				<c:forEach var="video" items="${sessionScope.AllVideos}">
					<li class="col-lg-3 col-sm-4 col-xs-6"><a
						href="${pageContext.request.contextPath}/watchVideo?v=${video.id}"
						title="${video.title}"> <img
							src="https://s3.eu-central-1.amazonaws.com/keep-calm-thumbnails/mqdefault.jpg"
							height="150px" width="150px" />
					</a></li
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>