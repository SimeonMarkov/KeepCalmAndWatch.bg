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
 //   setTimeout("window.location.href='login.html';",time);
 //example:

    setTimeout("window.location.href='index';", 8); // seconds
</script>
</head>
<body>

	<%@include file="header.jsp"%>
	<br>
	<br>
	<div>
		<p style="font-size: 20px;">${message}</p>
		<br> <a href="${pageContext.request.contextPath}/index"
			style="text-decoration: none; font-size: 20px; color: green;">Обратно към главната страница. </a>
			<br>
			<span style="text-decoration: none; font-size: 20px; color: green;">(ще бъдете автоматично пренасочени след няколко секунди))</span>>
	</div>
	</div>
	</div>
</body>
</html>