<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
#login-box {
	width: 400px;
	padding: 10px;
	margin: 100px auto;
	border: 1px solid transparent;
	border-radius: 2px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
	border: 1px solid #31708f;
}

.msg {
	padding: 10px;
	margin-bottom: 4px;
	border: 1px solid transparent;
	border-radius: 6px;
	color: #31708f;
	background-color: #d9edf7;
	border-color:#bce8f1;
}

</style>
</head>
<body bgcolor="#AAE6FA">

	<center>
	
	<div id="login-box">
		<div class="msg">Employee Section</div>
		<p><a href="http://localhost:8080/Project3/employee/list">Enter</a></p>
	</div>
	
	
	<div id="login-box">
		<div class="msg">Client Section</div>
		<p><a href="http://localhost:8080/Project3/client/list">Enter</a></p>
	</div>
	
	
	<div id="login-box">
		<div class="msg">Project Section</div>
		<p><a href="http://localhost:8080/Project3/project3/list">Enter</a></p>
	</div>
	
	</center>

</body>
</html>