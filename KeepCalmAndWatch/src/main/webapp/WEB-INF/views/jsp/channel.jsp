<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Канала на ${ChosenUser.channelName}</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/img/favicon.ico"
	type="image/png" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script type="text/javascript" src="js/channel.js"></script>
<link href="${pageContext.request.contextPath}/css/.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/channel.css"
	rel="stylesheet">

</head>
<body>

	<%@include file="header.jsp"%>

	<br>
	<br>

	<div class="col-md-1 col-lg-1"></div>
	<div class="col-lg-8 col-md-8">
		<div class="card hovercard">
			<div class="card-background">
				<img class="card-bkimg" alt="" src="">

			</div>
			<div class="useravatar">
				<img alt="" src="${ChosenUser.avatar}" height="150px" height="150px">
			</div>

		</div>
		<div class="btn-pref btn-group btn-group-justified btn-group-lg"
			role="group" aria-label="...">
			<div class="btn-group" role="group">
				<button type="button" id="favorites" class="btn btn-primary"
					onclick="location.href='#tab1'" data-toggle="tab">
					<span class="glyphicon glyphicon-star" aria-hidden="true"></span>
					<div class="hidden-xs">Информация</div>
				</button>
			</div>
			<div class="btn-group" role="group">
				<button type="button" id="favorites" class="btn btn-default"
					onclick="location.href='#tab2'" data-toggle="tab">
					<span class="glyphicon glyphicon-facetime-video" aria-hidden="true"></span>
					<div class="hidden-xs">Клипове</div>
				</button>
			</div>
			<div class="btn-group" role="group">
				<button type="button" id="following" class="btn btn-default"
					onclick="location.href='#tab3'" data-toggle="tab">
					<span class="glyphicon glyphicon-heart" aria-hidden="true"></span>
					<div class="hidden-xs">Любими</div>
				</button>
			</div>
		</div>


		<div class="well">
			<div class="tab-content">
				<div class="tab-pane fade in active" id="tab1">
					<div class="card-info">
						<h1><span class="card-title">${ChosenUser.channelName}</span></h1>
					</div>
					<c:if test="${not empty LoggedUser && ChosenUser.channelName ne LoggedUser.channelName}">
						<div id="subscribe">
							<c:if test="${empty subscribed}">
							<a href="subscribe?user=${ChosenUser.channelName}"
								class="btn btn-primary btn-sm" id="subscribe" type="submit">Абониране</a>
								</c:if>
								<c:if test="${not empty subscribed}">
							<a href="unSubscribe?user=${ChosenUser.channelName}"
								class="btn btn-primary btn-sm" id="subscribe" type="submit">Отписване</a>
								</c:if>
						</div>
					</c:if>
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
							<li class="col-lg-3 col-sm-4 col-xs-6"><h2><a
								href="${pageContext.request.contextPath}/watchVideo?v=${video.id}"
								title="${video.title}"> <img src="${video.thumbnail}"
									alt="Barca" class="img-responsive" height="130px" />
									${video.title}<span
									class="glyphicon glyphicon-play-circle"></span> <span
									class="duration">03:15</span>
							</a></h2></li>
						</c:forEach>
					</ul>
				</div>
				<div class="tab-pane fade in" id="tab3">
					<ul class="list-unstyled video-list-thumbs row">
						<c:forEach var="video" items="${favorites}">
							<li class="col-lg-3 col-sm-4 col-xs-6"><h2><a
								href="${pageContext.request.contextPath}/watchVideo?v=${video.id}"
								title="${video.title}"> <img src="${video.thumbnail}"
									alt="Barca" class="img-responsive" height="130px" />
									${video.title} <span
									class="glyphicon glyphicon-play-circle"></span> <span
									class="duration">03:15</span>
							</a></h2></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>

	</div>
</body>
</html>