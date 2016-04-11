<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Регистрация на потребител</title>
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

</head>

<body>


	<%@include file="header.jsp"%>

	<br>
	<br>
	<br>
	<div class="row centered-form">
	<div
			class="col-xs-9 col-sm-9 col-md-3 col-lg-3 "></div>
		<div
			class="col-xs-9 col-sm-9 col-md-5 col-lg-5 ">
			<div class="panel panel-default">
				<div class="panel-heading">
					<p>
						<font color="red">${fail}</font>
					</p>
					<h3 class="panel-title">Регистрация на потребител</h3>
				</div>
				<div class="panel-body">
					<form:form name="regform" 
						method="POST" action="/KeepCalmAndWatch/registerUser" role="form">
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">
									<form:input path="username" type="text" name="username"
										id="username" class="form-control input-sm"
										placeholder="Потребителско име" />
								</div>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">
									<form:input path="email" type="email" name="email" id="email"
										class="form-control input-sm" placeholder="Имейл адрес" />
								</div>
							</div>
						</div>

						<div class="form-group">
							<form:input path="channelName" type="text" name="channelName"
								id="channelName" class="form-control input-sm"
								placeholder="Име на канала" />
						</div>


						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">

									<form:input path="password" type="password" name="password"
										id="password" class="form-control input-sm"
										placeholder="Парола" />
								</div>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-6">
								<div class="form-group">
									<input path="passwordconfirmation" type="password"
										name="passwordconfirmation" id="password_confirmation"
										class="form-control input-sm"
										placeholder="Потвърдете паролата">
								</div>
							</div>
						</div>

						<input type="submit" value="Регистрация"
							class="btn btn-custom btn-block">

					</form:form>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>


</body>
</html>