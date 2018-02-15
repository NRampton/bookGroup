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
			<h4>Now let's invite some people to read ${newGroup.subjectBook.title} with you.</h4>
			<h5>Invited:</h5>
			<c:forEach items="${newGroup.invitees}" var="invitee">
				<p>${invitee.username} ( <a href="/groups/uninvite/${invitee.id}">Remove</a> )</p>
			</c:forEach>
			<form method="POST" action="/groups/invite">
				<fieldset>
					<legend>Invite some other people:</legend>
					<c:forEach items="${uninvitedUsers}" var="user">
						<p>
							<label><input type="checkbox" value="${user.username}" /> ${user.username}</label>
						</p>
					</c:forEach>
					<input type="submit" value="Invite" />
				</fieldset>
			</form>
		</div>
	</body>
</html>
