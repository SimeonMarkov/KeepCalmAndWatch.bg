<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<link href="${pageContext.request.contextPath}/css/headerAndSidenav.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/channel.css"
	rel="stylesheet">
</head>
<body>

			
	<%@include file="header.jsp"%>		
			
			<br><br><br>
			
			<c:if test="${not empty LoggedUser}">
				<p>Hello,${LoggedUser.channelName}</p>
			</c:if>
			<br />
			<table>

				<c:forEach var="user" items="${requestScope.ChannelNameLike}">
					<tr>
						<td>
							<p>${user.channelName}</p>
						</td>
						<td><a
							href="${pageContext.request.contextPath}/channel?user=${user.channelName}"><img
								src="${user.avatar}" width="50px"
								height="50px" /></a><br /> <br /></td>
					</tr>
				</c:forEach>


			</table>
			<br>
			<br>
			<p style="font-size: 30px;">${Category}</p>
			<div class="tab-pane fade in" id="tab2">
				<ul class="list-unstyled video-list-thumbs row">
					<c:forEach var="video" items="${requestScope.VideoSearch}">
						<li class="col-lg-3 col-sm-4 col-xs-6"><a
							href="${pageContext.request.contextPath}/watchVideo?v=${video.id}"
							title="${video.title}"> <img
								src="${video.thumbnail}" width="150px" height="150px" alt="Barca"
								class="img-responsive" width="auto" height="100px" />
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