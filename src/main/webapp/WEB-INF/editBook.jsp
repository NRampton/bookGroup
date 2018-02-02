<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${ currentBook.title }</title>
		<link rel="stylesheet" type="text/css" href="/css/style.css">	
		<script src="/js/main.js"></script>
	</head>

	<body>
		<form:form action="/books/edit/${currentBook.id}" method="POST" modelAttribute="currentBook">
			<p>
				<form:label path="title">Title: 
					<form:input path="title" />
					<p><form:errors path="title" /></p>
				</form:label>
			</p>
			<p>
				<form:label path="author">Author: 
					<form:input path="author" />
					<p><form:errors path="author" /></p>
				</form:label>
			</p>
			<p>
				<label>Tags:
					<input name="newTags" value="${tags}" />
				</label>
			</p>
			<button type="submit">Submit changes</button>
		</form:form>
	</body>
</html>
