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
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/img/favicon.ico"
	type="image/png" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link href="${pageContext.request.contextPath}/css/headerAndSidenav.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<script>
	$(document)
			.ready(
					function() {
						$('#videoUpload')
								.change(
										function() {
											var val = $(this).val()
													.toLowerCase();
											var regex = new RegExp(
													"(.*?)\.(ogv|mp4)$");
											if (!(regex.test(val))) {
												$(this).val('');
												alert('Please select a file with extension either .ogv or .mp4 for a video!');
											}
										});
					});
</script>
<script>
	$(document)
			.ready(
					function() {
						$('#thumbnailUpload')
								.change(
										function() {
											var val = $(this).val()
													.toLowerCase();
											var regex = new RegExp(
													"(.*?)\.(jpg|png)$");
											if (!(regex.test(val))) {
												$(this).val('');
												alert('Please select a file with extension either .jpg or .png for a thumbnail!');
											}
											if (val == null || val == "") {
												$(this).val('');
												alert('Please select a file to upload!');
											}
										});
					});
</script>
</head>
<body>

	<%@include file="header.jsp"%>
	<br>
	<br>
	<br>
	<div class="row centered-form">
		<div
			class="col-xs-9 col-sm-9 col-md-6 col-lg-6 col-sm-offset-3 col-md-offset-3 col-lg-offset-3">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">
						Здравейте,<b> ${LoggedUser.channelName}!</b> Изберете видео файл,
						който искате да качите:
					</h3>
				</div>
				<div class="panel-body">
					<form action="upload" method="POST" enctype="multipart/form-data"
						id="uploadform" accept-charset="UTF-8">
						<br> Изберете клип за чакване(MP4/OGV): <br><br>
						 <input type="file" id="videoUpload" name="videoPath"
							required="required" /> <br> <br>  
							<input type="text"
							name="title" placeholder="Заглавие на клипа..."
							required="required" /> <br> <br>
						<textarea form="uploadform" rows="4" cols="50" name="description"
							placeholder="Описание (500 символа)"></textarea>
						<br> <br>
						<div class="form-group">
							 Изберете изображение за показване(JPG/PNG): <br><br> <input
								type="file" id="thumbnailUpload" name="thumbnail"
								required="required" />
						</div>
						<div>
							Изберете категория: <br> <br> <select name="category">
								<option value="Music">Музика</option>
								<option value="Games">Игри</option>
								<option value="Sport">Спорт</option>
								<option value="Funny">Забавни</option>
								<option value="News">Новини</option>
							</select>
						</div>

						<br> <input type="submit" value="Качи" class="btn btn-custom">
					</form>
				</div>
			</div>
		</div>
	</div>

	</div>
	</div>
</body>
</html>