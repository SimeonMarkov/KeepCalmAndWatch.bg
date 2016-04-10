<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.error{
	color: #b30000;
	font-weight: bold;
	font-size: 24px;
}
</style>
</head>
<body>
	<c:if test="${not empty requestScope.badUrl}">
		<p class="error"><c:out value="Sorry,video with this URL doesn't exist!"></c:out></p>
	</c:if>
	<c:if test="${not empty requestScope.noSuchUser}">
		<p class="error"><c:out value="Sorry,such channel name doesnt exist!"></c:out><p class="error">
	</c:if>
	<a href="${pageContext.request.contextPath}/index" style="text-decoration: none">Back to home page</a>
</body>
</html>