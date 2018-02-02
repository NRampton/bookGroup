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
			<h2>Tag: ${currentTag.name}</h2>
			<p><a href="/users/dashboard">Back to dashboard</a></p>
			<h4>Books with this tag:</h4>
			<table>
				<thead>
					<tr>
						<th>Title</th>
						<th>Author</th>
						<th>Other Tags</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${booksSans}" var="book">
						<tr>
							<td>${book.title }</td>
							<td>${book.author }</td>
							<td> 
								<c:forEach items="${book.tags}" var="tag">
									<span><a href="/tags/${tag.id}">| ${tag.name} |</a></span>
								</c:forEach>	
							</td>			
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>
