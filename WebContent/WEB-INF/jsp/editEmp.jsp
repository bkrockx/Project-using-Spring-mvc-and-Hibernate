<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
#Table-box {
	width: 600px;
	padding: 20px;
	margin: 100px auto;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
	border: 1px solid #31708f;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

.msg1{
	color:red
}
</style>
</head>
<body bgcolor="#AAE6FA">

<center>
<div id="Table-box">
<h1>Edit Existing Employee</h1>

<c:url var="saveUrl" value="/employee/editEmp?id=${employeeAttribute.employeeId}" />
<form:form modelAttribute="employeeAttribute" method="POST" action="${saveUrl}">
	<table>
		
		<tr>
			<td><form:label path="employeeId"></form:label></td>
			<td><form:input path="employeeId" type="hidden"/></td>
		</tr>

		<tr>
			<td><form:label path="employeeName">Employee Name:</form:label></td>
			<td><form:input path="employeeName"/></td>
		</tr>
		
		<tr>
			<td><form:label path="designation">designation:</form:label></td>
			<td><form:input path="designation"/></td>
		</tr>
		
		<tr>
			<td><form:label path="department">department:</form:label></td>
			<td><form:input path="department"/></td>
		</tr>
		
		<tr>
			<td><form:label path="password">password:</form:label>
			<td><form:input path="password"/></td>
		</tr>
		
	</table>
	
	<input type="submit" value="Save" />
</form:form>
</div>
</center>
</body>
</html>