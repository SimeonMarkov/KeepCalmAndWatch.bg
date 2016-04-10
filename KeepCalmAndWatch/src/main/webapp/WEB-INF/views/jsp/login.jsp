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


<body>

<%@include file="header.jsp"%>

	<div class="container-fluid">
		<div class="row content">
<br><br><br>
			<div class="row centered-form">
				<div
					class="col-xs-9 col-sm-9 col-md-4 col-sm-offset-3 col-md-offset-13">
					<div class="panel panel-default">
						<div class="panel-heading">
							<p style="color: red">${fail}</p>
							<h3 class="panel-title">Вход</h3>
						</div>
						<div class="panel-body">
							<form:form name="loginForm2" onsubmit="return validateLogin2()"
								method="POST" action="/KeepCalmAndWatch/login" role="form">
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group">
											<form:input path="username" type="text" name="username"
												id="username" class="form-control input-sm"
												placeholder="Потребителско име" />
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-xs-6 col-sm-6 col-md-6">
										<div class="form-group">
											<form:input path="password" type="password" name="password"
												id="password" class="form-control input-sm"
												placeholder="Парола" />
										</div>
									</div>
								</div>
								<input type="submit" value="Вход"
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