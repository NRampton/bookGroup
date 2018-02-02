<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Index</title>
		<link rel="stylesheet" type="text/css" href="/css/style.css">	
		<script src="/js/main.js"></script>
	</head>

	<body>
		<div id="wrapper">
			<h2>Title: ${currentBook.title}</h2>
			<p>Author: ${currentBook.author}</p>
			<p>Tags: </p>
			<p>
				<c:forEach items="${currentBook.tags}" var="tag">
					<span>| ${tag.name} |</span>
				</c:forEach>
			</p>
			<p>Other readers interested in this book:</p>
			<ul>
				<c:forEach items="${currentBook.users}" var="user">
					<c:if test="${user.id != currentUser.id}">
						<li>${user.username}</li>
					</c:if>	
				</c:forEach>
			</ul>
			<p><a href="/books/edit/${ currentBook.id }">Edit this book's record</a></p>
		</div>

	</body>
</html>
