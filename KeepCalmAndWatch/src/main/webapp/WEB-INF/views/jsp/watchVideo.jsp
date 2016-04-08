<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
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
<title>My video page</title>
<link href="${pageContext.request.contextPath}/css/headerAndSidenav.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/channel.css"
	rel="stylesheet">
<style>
.form-control {
	display: block;
	overflow: hidden;
	resize: none;
	padding: 10px;
	width: 640px;
	font-size: 14px;
	border-radius: 8px;
}

.suggestions {
	position: absolute;
	top: 10px;
	width: 380px;
	right: 10px;
	border: 1px solid black;
}

.suggestions li {
	list-style-type: none;
}

a {
	color: black;
}

a:hover {
	text-decoration: none;
	color: blue;
}

.description {
	width: 940px;
	border: 1px solid black;
}

.rateVideo {
	text-decoration: none;
}

.container-fluid {
	margin-top: 100px;
	border: 1px solid black;
	width: 940px;
	float: left;
}

.currentVideo {
	position: relative;
	top: 10px;
}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script>
	$(document).one('focus.textarea', '.form-control', function() {
		var savedValue = this.value;
		this.value = '';
		this.baseScrollHeight = this.scrollHeight;
		this.value = savedValue;
	}).on('input.textarea', '.form-control', function() {
		var minRows = this.getAttribute('data-min-rows') | 0, rows;
		this.rows = minRows;
		console.log(this.scrollHeight, this.baseScrollHeight);
		rows = Math.ceil((this.scrollHeight - this.baseScrollHeight) / 17);
		this.rows = minRows + rows;
	});
</script>
<script>
	
</script>
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
	<c:if test="${not empty sessionScope.LoggedUser}">
		<div id="avatar">
			<img class="img-circle" alt="N/A" width="50" height="50"
				src="data:image/gif;base64,${LoggedUser.avatar}" />
		</div>
	</c:if>
	<div class="dropdown">
		<button class="btn btn-default dropdown-toggle" type="button"
			id="menu1" data-toggle="dropdown">
			<span class="caret"></span>
		</button>
		<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
			<li role="presentation"><a role="menuitem" tabindex="-1"
				href="${pageContext.request.contextPath}/index">Начална страница</a></li>
			<c:if test="${not empty sessionScope.LoggedUser}">
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="${pageContext.request.contextPath}/profile">Моят канал</a></li>
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="#">Абонаменти</a></li>

				<li role="presentation" class="divider"></li>
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="${pageContext.request.contextPath}/logout">Изход</a></li>
			</c:if>
			<c:if test="${empty sessionScope.LoggedUser}">
				<li role="presentation" class="divider"></li>
				<li role="presentation"><a role="menuitem" tabindex="-1"
					href="${pageContext.request.contextPath}/login">Влез</a></li>
			</c:if>
		</ul>
	</div>

	<c:out value="${video.path}"></c:out>
	<div style="position: relative; top: 50px;">
		<div class="currentVideo"
			style="width: 940px; height: 590px; border: 1px solid black;">
			<video width="940" height="590" controls tabindex="0"
				poster="${video.thumbnail}" autoplay>
				<source src="${video.path}" type="video/mp4" />
				<source src="${video.path}" type="video/ogv" />
				<source src="${video.path}" type="video/webm" />
			</video>
			<div>
				<h2>${video.title}</h2>
			</div>
			<div class="description">${video.description}</div>
		</div>

		<div class="container-fluid">
			<div class="row content">


				<div class="col-sm-9">

					<h4>Leave a Comment:</h4>
					<form>
						<div class="form-group">
							<textarea class="form-control" rows="2" data-min-rows='2'
								placeholder="Напишете коментар..." required></textarea>
						</div>
						<button type="submit" class="btn btn-success">Submit</button>
					</form>
					<br> <br>

					<p>
						<span class="badge"></span> Comments:
					</p>
					<br>
					<div class="dropdown">
						<button class="btn btn-default dropdown-toggle" type="button"
							data-toggle="dropdown">
							Сортирай по <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a href="#">Най-харесвани</a></li>
							<li><a href="#">Най-нови</a></li>
						</ul>
					</div>

					<div class="row">
						<c:forEach var="comment" items="${requestScope.comments}">
							<div class="col-sm-2 text-center">
								<img src="data:image/gif;base64,${comment.user.avatar}" class="img-circle" height="65"
									width="65" alt="Avatar">
							</div>
							<div class="col-sm-10">
								<h4>
									${comment.user.channelName} <small>${comment.datetime}</small>
								</h4>
								<p>${comment.text}</p>
								<br>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>


		<div class="suggestions">
	
		<div class="tab-pane fade in" id="tab2">
				<ul class="list-unstyled video-list-thumbs row">
					<c:forEach var="video" items="${sessionScope.AllVideos}">
						<li class="col-lg-3 col-sm-4 col-xs-6"><a
							href="${pageContext.request.contextPath}/watchVideo?v=${video.id}"
							title="${video.title}"> <img
								src="data:image/gif;base64,${video.thumbnail}" alt="Barca"
								class="img-responsive" width ="auto" height="100px" />
								<h2>${video.title}</h2> <span
								class="glyphicon glyphicon-play-circle" ></span> <span
								class="duration">03:15</span>
						</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>

</body>
</html>