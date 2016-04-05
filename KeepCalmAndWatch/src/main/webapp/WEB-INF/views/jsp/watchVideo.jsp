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

	<div class="input-group">
		<input type="text" class="form-control" placeholder="Търсене..">
		<span class="input-group-btn">
			<button class="btn btn-default" type="button">
				<span class="glyphicon glyphicon-search"></span>
			</button>
		</span>
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
				href="#">Моят канал</a></li>
			<li role="presentation"><a role="menuitem" tabindex="-1"
				href="#">Абонаменти</a></li>
			<li role="presentation" class="divider"></li>
			<li role="presentation"><a role="menuitem" tabindex="-1"
				href="${pageContext.request.contextPath}/logout">Изход</a></li>
		</ul>
	</div>
	<div style="position: relative; top: 50px;">
		<div class="currentVideo"
			style="width: 940px; height: 590px; border: 1px solid black;">
			<video width="940" height="590" controls tabindex="0"
				poster="D:/picthas/source.jpg">
				<source src="C:\Users\Simo\Desktop\otkrihmeYoutube\snoop.ogv" type="video/ogg" />
				<source src="C:\Users\Simo\Desktop\otkrihmeYoutube\snoop.mp4" type="video/mp4" />
				<source src="C:\Users\Simo\Desktop\otkrihmeYoutube\snoop.mp4" type="video/webm" />
			</video>
			<div>
				<h2>Some video</h2>
			</div>
			<div class="description">Description of the video</div>
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
						<span class="badge">${video.comments}</span> Comments:
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
						<div class="col-sm-2 text-center">
							<img src="bandmember.jpg" class="img-circle" height="65"
								width="65" alt="Avatar">
						</div>
						<div class="col-sm-10">
							<h4>
								Anja <small>Sep 29, 2015, 9:12 PM</small>
							</h4>
							<p>Keep up the GREAT work! I am cheering for you!! Lorem
								ipsum dolor sit amet, consectetur adipiscing elit, sed do
								eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
							<br>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="suggestions">
			
			<c:forEach var="video" items="${sessionScope.AllVideos}">
			<a href="${pageContext.request.contextPath}/watchVideo?v=${video.id}">
				<table>
					<tr>
						<td><img src="data:image/gif;base64,${video.thumbnail}" width="50px" height="50px" /><br/>
						<div>
							<span class="mealName">${video.title}</span>
						</div>
						<div class="divIngredients">
								<span class="mealIngredients">${video.views}</span>
							</div>
						</td>
					</tr>
					
				</table>
			</a><br> 
			</c:forEach>	
		</div>
	</div>

</body>
</html>