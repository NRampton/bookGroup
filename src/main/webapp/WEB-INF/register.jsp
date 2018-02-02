<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Register or Login</title>
		<link rel="stylesheet" type="text/css" href="/css/style.css">	
		<script src="/js/main.js"></script>
	</head>

	<body>
		<div id="wrapper">
			<h2 id="motto">Book</h2>
			<h4>About that book you've been meaning to get around to...</h4>
			<div id="register">
				<h3>Registration</h3>
				<form:form action="/users/registration" method="POST" modelAttribute="user">
					<p>
						<form:label path="username">Username: 
							<form:input path="username"></form:input>
						</form:label>
					</p>
					<p>
						<form:label path="email">Email: 
							<form:input path="email"></form:input>
						</form:label>
					</p>
					<p class="aside">We only use these for password reset</p>
					<p>
						<form:label path="password">Password: 
							<form:input type="password" path="password"></form:input>
						</form:label>
					</p>
					<p>
						<form:label path="confirm">Confirm password: 
							<form:input type="password" path="confirm"></form:input>
						</form:label>
					</p>
					<button type="submit">Register</button>
				</form:form>
				<form:errors class="errors" path="user.*" />
			</div>
			<div id="login">
				<h3>Login:</h3>
				<form method="POST" action="/users/login">
					<p><label>Username: <input type="text" name="username"></label></p>
					<p><label>Password: <input type="password" name="password"></label></p>
					<p><button type="submit">Login</button></p>
					<p class="errors">${ error }</p>
				</form>
			</div>
		</div>
	</body>
</html>
