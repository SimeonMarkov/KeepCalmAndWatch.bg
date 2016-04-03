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
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/favicon.ico" type="image/png" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
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
	top: 10px;
	left: 400px;
}

#upload {
	position: absolute;
	top: 10px;
	right: 100px;
}

#avatar {
	position: absolute;
	top: 5px;
	right: 25px;
}
</style>
<script>
	$(document).ready(function() {
		$('#videoUpload').change(function() {
			var val = $(this).val().toLowerCase();
			var regex = new RegExp("(.*?)\.(ogv|mp4)$");
			if (!(regex.test(val))) {
				$(this).val('');
				alert('Please select a file with extension either .ogv or .mp4 for a video!');
			}
		});
	});
</script>
<script>
	$(document).ready(function() {
		$('#thumbnailUpload').change(function() {
			var val = $(this).val().toLowerCase();
			var regex = new RegExp("(.*?)\.(jpg|png)$");
			if (!(regex.test(val))) {
				$(this).val('');
				alert('Please select a file with extension either .jpg or .png for a thumbnail!');
			}
			if(val == null || val == ""){
				$(this).val('');
				alert('Please select a file to upload!');
			}
		});
	});
</script>
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
				width="50px" height="50px" /></a> <a href="upload"
				class="btn btn-primary btn-sm" id="upload" type="submit">Качване</a>
			<img class="img-circle" id="avatar" alt="Cinque Terre" width="50"
				height="50"
				src="http://www.forplay.bg/forums/avs/avatar_114_1446077663.png" />
			<div class="input-group">
				<input type="text" class="form-control" placeholder="Search Blog..">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">
						<span class="glyphicon glyphicon-search"></span>
					</button>
				</span>
			</div>
			<div>
				<br>
				<form:form commandName="">
					<label>Здравейте,${LoggedUser.channelName}!Изберете видео
						файл,който искате да качите:</label>
					<br>
					<input type="file" id="videoUpload" name="videoPath" required="required" />
					<br>
					<br>
					<input type="text" name="title" placeholder="Insert video title..."  required="required" />
					<br>
					<br>
					<input type="text" name="description" placeholder="Add description..." />
					<br>
					<br>
					<input type="file" id="thumbnailUpload" name="thumbnail"  required="required" />
					<br>
					<br>
					<input type="submit" value="Качи">
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>