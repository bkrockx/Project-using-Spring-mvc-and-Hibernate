<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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


<c:url var="addUrl" value="/project3/add" />
<c:url var="EmpPage" value="http://localhost:8080/Project3/employee/list" />
<c:url var="CntPage" value="http://localhost:8080/Project3/client/list"/>

<div id="Table-box">
<div class="msg1">
<b>Projects</b>
</div>
<table border='1.5' width='600' cellpadding='1' cellspacing='1'>
	<tr>
		<th>ProjectId</th>
		<th>ProjectName</th>
		<th>Description</th>
		<th>Manager</th>
		<th>FTE</th>
	
		<th>Edit</th>
		<th>Delete</th>
	</tr>
	<tbody>
	<c:forEach items="${projects}" var="project">
		<c:url var="editUrl" value="/project3/edit?id=${project.projectId}" />
		<c:url var="deleteUrl" value="/project3/delete?id=${project.projectId}" />
	
			<tr>
				<td><c:out value="${project.projectId}" /></td>
				<td><c:out value="${project.projectName}" /></td>
				<td><c:out value="${project.description}" /></td>
				<td><c:out value="${project.manager}" /></td>
				<td><c:out value="${project.fte}" /></td>
	
				<td><a href="${editUrl}">Edit</a></td>
				<td><a href="${deleteUrl}">Delete</a></td>

			</tr>
			</c:forEach>
			
	</tbody>
</table>
<p><a href="${addUrl}">Create New Project</a></p>
<p><a href="${EmpPage}">Employee Screen</a>
<p><a href="${CntPage}">Client Screen</a>
</div>

<div id="login-box">
	<p><a href="http://localhost:8080/Project3/project3/Logout">Logout</a></p>
</div>
</center>
</body>
</html>