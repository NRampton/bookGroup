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
			<h1>All the books!</h1>
			<p>Seriously, this is too many books. We're working on a search field to narrow it down.</p>
			<p>(But in the meantime you could always <a href="/books/new">add another</a>...)</p> 
			<p>Or <a href="/users/dashboard">go back</a> to your dashboard.</p>
			<table>
				<thead>
					<tr>
						<th>Title</th>
						<th>Author</th>
						<th>tags</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${allBooks}" var="book">
							<tr>
								<td>${book.title}</td>
								<td>${book.author}</td>
								<td>
									<c:forEach items="${ book.tags }" var="tag">
										<span><a href="/tags/${tag.id}">| ${ tag.name } |</a></span>						<!-- to do: links inside tables should be unstyled -->
									</c:forEach>
								</td>
								<td>
									<c:if test="${currentUser.books.contains(book)}">
										<a href="books/remove/${book.id}">Remove from my list</a>
									</c:if>
									<c:if test="${!currentUser.books.contains(book)}">
										<a href="books/add/${book.id }">Add to my list</a>
									</c:if>
								</td>
							</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</body>
</html>
