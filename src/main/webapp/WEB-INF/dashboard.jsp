<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Dashboard</title>
		<link rel="stylesheet" type="text/css" href="/css/style.css">	
		<script src="/js/main.js"></script>
	</head>

	<body>
		<div id="wrapper">
			<div id="header">
				<h2>Oh, hi ${currentUser.username}.</h2>
				<a href="/logout">logout</a>
			</div>
			<div id="main">
				<div id="groups">
					<h4>Upcoming groups—get reading!</h4>
					<table>
						<thead>
							<tr>
								<th>Name</th>
								<th>Book</th>
								<th>Date</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${currentUser.groups}" var="group">
								<tr>
									<td>${group.name}</td>
									<td class="title">${group.subjectBook.title}</td>
									<td>${group.date}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<div id="book_list">
					<h4>Books you're gonna read.</h4>
					<table>
						<thead>
							<tr>
								<th>Title</th>
								<th>Author</th>
								<th>Tags</th>
								<th>Actions</th>
							</tr>
						</thead>
						<tbody id="dashboard_books">
							<c:forEach items="${currentUser.books}" var="book">
									<tr>
										<td><em><a class="table_link" href="/books/${book.id}">${book.title}</a></em></td>
										<td>${book.author}</td>
										<td class="tag_box">
											<c:forEach items="${ book.tags }" var="tag">
												<span><a class="table_link" href="/tags/${tag.id}">| ${ tag.name } |</a></span>						<!-- to do: links inside tables should be unstyled -->
											</c:forEach>
										</td>
										<td>
											<a class="table_link" href="/books/edit/${book.id}">Edit </a>
											<a class="table_link" href="/books/remove_dash/${book.id}">remove</a><br/>
											<a class="table_link" href="/groups/new/${book.id}">Start a group</a>
										</td>
									</tr>
							</c:forEach>
						</tbody>
					</table>
					<a href="/books">Add a book to your list</a>
				</div>
			</div>
		</div>
	</body>
</html>
