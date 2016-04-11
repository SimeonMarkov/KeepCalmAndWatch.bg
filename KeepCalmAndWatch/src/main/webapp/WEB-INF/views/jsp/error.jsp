<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Възникна грешка</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/img/favicon.ico"
	type="image/png" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/comment_insert.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<title>My video page</title>
<link href="${pageContext.request.contextPath}/css/headerAndSidenav.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/channel.css"
	rel="stylesheet">
<style>
.error{
	color: #b30000;
	font-weight: bold;
	font-size: 24px;
}

body  {
    background-image: url("img/notFound.jpg");
    background-color: #cccccc;
}
</style>
</head>
<body>
	<a href="${pageContext.request.contextPath}/index" class="btn btn-warning" role="button" style="position: absolute; top: 95px; left: 200px;">Back to home page</a>
</body>
</html>