<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${newGroup.subjectBook.title}</title>
		<link rel="stylesheet" type="text/css" href="/css/style.css">	
		<script src="/js/main.js"></script>
	</head>

	<body>
		<div id="wrapper">
			<h1>Let's read <em>${newGroup.subjectBook.title}!</em></h1>
			<form:form method="POST" action="/groups/createStageOne" modelAttribute="newGroupAddl">
				<p>
					<form:label path="name">Group name: 
						<form:input path="name"></form:input>
						<p>
							<form:errors path="name"></form:errors>
						</p>
					</form:label>
				</p>
				<p>
					<form:label path="location">Location: 
						<form:input path="location"></form:input>
						<p class="aside">ex: "Silver Spring, MD" or "GChat"</p>
						<p>
							<form:errors path="location"></form:errors>
						</p>
					</form:label>
				</p>
				<%-- <p>
					<form:label path="date">Date: 
						<form:input type="date" path="date"></form:input>
						<p>
							<form:errors path="date"></form:errors>
						</p>
					</form:label>
				</p>
				<p>
					<form:label path="time">Time:
						<form:input type="time" path="time"></form:input>
						<p>
							<form:errors path="time"></form:errors>
						</p>
					</form:label>
				</p> --%>
			<p><button type="submit">Create group</button></p>
			<p class="aside">You'll have a chance to invite other users on the next page...</p>
			<p class="aside">Because if it's just you, you know, it's not really a "group."</p>
			</form:form>
		</div>
	</body>
</html>