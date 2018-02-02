<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Add a new book</title>
		<link rel="stylesheet" type="text/css" href="/css/style.css">	
		<script src="/js/main.js"></script>
	</head>

	<body>
		<div id="wrapper">
			<h2>Add a new book to our database:</h2>
			<form:form action="/books/new" method="POST" modelAttribute="book">
				<p>
					<form:label path="title">Title: 
						<form:input path="title"></form:input>
						<form:errors path="title"></form:errors>
					</form:label>
				</p>
				<p>
					<form:label path="author">Author: 
						<form:input path="author"></form:input>
						<form:errors path="author"></form:errors>
					</form:label>
				</p>
				<p>
					<label>Tags: <input type="text" name="newTags"></label>				<!--add validations for fewer than three tags -->
				</p>
				<p class="aside">(Enter up to three tags)</p>
				<button type="submit">Add</button>
			</form:form>
		</div>
	</body>
</html>
