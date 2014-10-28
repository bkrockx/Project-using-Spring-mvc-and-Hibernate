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
</style>
</head>
<body bgcolor="#AAE6FA">
<center>
<div id="Table-box">

<h1>Edit Existing Record</h1>

<c:url var="saveUrl" value="/project/edit?id=${projectAttribute.projectId}" />
<c:url var="addCUrl" value="/employee/add?id=${projectAttribute.projectId}" />
<c:url var="addDUrl" value="/client/add?id=${projectAttribute.projectId}" />

<form:form modelAttribute="projectAttribute"  method="POST" action="${saveUrl}">
	<table>
		<tr>
			<td><form:label path="projectId">ProjectId:</form:label></td>
			<td><form:input path="projectId" disabled="true"/></td>
		</tr>
	
		<tr>
			<td><form:label path="ProjectName">ProjectName:</form:label></td>
			<td><form:input path="ProjectName"/></td>
		</tr>
		<tr>
			<td><form:label path="description">description:</form:label></td>
			<td><form:input path="description"/></td>
		</tr>
		<tr>
			<td><form:label path="manager">Manager:</form:label></td>
			<td><form:input path="manager"/></td>
		</tr>
		<tr>
			<td><form:label path="fte">FTE:</form:label></td>
			<td><form:input path="fte"/></td>
		</tr>
		
	</table>
	<input type="submit" value="Save" />
	
	<table border='1.5' width='600' cellpadding='1' cellspacing='1'>
	<tr>
		<th>EmployeeId</th>
		<th>EmployeeName</th>
		<th>Designation</th>
		<th>Department</th> 
		
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<tbody>
	
	
		<c:forEach items="${projectAttribute.employee}" var="employee">
		<tr>
				<c:url var="editCUrl" value="/employee/edit?bid=${projectAttribute.projectId}&cid=${employee.employeeId}" />
				<c:url var="deleteCUrl" value="/employee/delete?id=${employee.employeeId}" />
				
				<td><c:out value="${employee.employeeId}" /></td>
				<td><c:out value="${employee.employeeName}"/></td>
				<td><c:out value="${employee.designation}"/></td>
				<td><c:out value="${employee.department}"/></td>
		 		
				<td><a href="${editCUrl}">Edit</a></td>
				<td><a href="${deleteCUrl}">Delete</a></td>

		</tr>	
		</c:forEach>
	
		
		  <p><a href="${addCUrl}">Add Employee</a></p> 
		
	
	</tbody>
</table>
<table border='1.5' width='600' cellpadding='1' cellspacing='1'>
	<tr>
		<th>ClientId</th>
		<th>ClientName</th>
		<th>ClientAddress</th> 
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<tbody>
	
	
		<c:forEach items="${projectAttribute.client}" var="client">
		<tr>
				<c:url var="editDUrl" value="/client/edit?bid=${projectAttribute.projectId}&cid=${client.clientId}" />
				<c:url var="deleteDUrl" value="/client/delete?id=${client.clientId}" />
				
				<td><c:out value="${client.clientId}" /></td>
				<td><c:out value="${client.clientName}"/></td>
				<td><c:out value="${client.clientAddress}"/></td>
		 
				<td><a href="${editDUrl}">Edit</a></td>
				<td><a href="${deleteDUrl}">Delete</a></td>

		</tr>	
		</c:forEach>
	
		
		  <p><a href="${addDUrl}">Add Client</a></p> 
		
	
	</tbody>
</table>
</form:form>
</div>

</center>
</body>
</html>