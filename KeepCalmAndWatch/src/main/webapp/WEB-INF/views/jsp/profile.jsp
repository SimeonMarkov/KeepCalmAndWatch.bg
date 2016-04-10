<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Вашият профил</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/img/favicon.ico"
	type="image/png" />
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<link href="${pageContext.request.contextPath}/css/headerAndSidenav.css"
	rel="stylesheet">
</head>
</head>
<body>

<%@include file="header.jsp"%>

<div class="container">
  <h1 class="page-header">${user.username }</h1>
  <div class="row">
    <!-- left column -->
    <div class="col-md-4 col-sm-6 col-xs-12">
      <div class="text-center">
      <c:if test="${empty user.avatar}"> 
        <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/217538/default-avatar-ponsy-deer.png" class="avatar img-circle img-thumbnail" alt="">
      </c:if>
      <c:if test="${not empty user.avatar}">
     	 <img src="${LoggedUser.avatar}" class="avatar img-circle img-thumbnail" alt="" />
      </c:if>
        <h6>Качване на друга снимка</h6>
        <input type="file" class="text-center center-block well well-sm">
      </div>
    </div>

    <div class="col-md-8 col-sm-6 col-xs-12 personal-info">
      <div class="alert alert-info alert-dismissable">
        <a class="panel-close close" data-dismiss="alert"></a> 
        <i class="fa fa-coffee"></i>
        
      
      <form class="form-horizontal" role="form">
        <div class="form-group">
          <label class="col-lg-3 control-label">Име на канала:</label>
          <div class="col-lg-8">
            <input class="form-control" value="${user.channelName }" type="text">
          </div>
        </div>
        <div class="form-group">
          <label class="col-lg-3 control-label">Описание на канала:</label>
          <div class="col-lg-8">
            <textarea class="form-control" rows="4" cols="50">${user.description }</textarea>
          </div>
        </div>
        <div class="form-group">
          <label class="col-lg-3 control-label">Имейл:</label>
          <div class="col-lg-8">
            <input class="form-control" value="${user.email }" type="text">
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 control-label">Нова парола:</label>
          <div class="col-md-8">
            <input class="form-control" type="password">
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 control-label">Потвърдете новата парола:</label>
          <div class="col-md-8">
            <input class="form-control" type="password">
          </div>
        </div>
           <div class="form-group">
          <label class="col-md-3 control-label">Стара парола:</label>
          <div class="col-md-8">
            <input class="form-control" type="password">
          </div>
        </div>
        <div class="form-group">
          <label class="col-md-3 control-label"></label>
          <div class="col-md-8">
            <input class="btn btn-primary" value="Запазете промените" type="button"></div>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>