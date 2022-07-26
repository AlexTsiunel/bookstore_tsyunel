<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Users</title>
</head>
<body>
	<table>
		<th>#</th>
		<th>Id</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Email</th>
		<th>Role</th>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><a href="user?id=${user.id}">${user.id}</a></td>
				<td><a href="book?id=${user.id}">${user.firstName}</a></td>
				<td><a href="book?id=${user.id}">${user.lastName}</a></td>
				<td><a href="book?id=${user.id}">${user.email}</a></td>
				<td><a href="book?id=${user.id}">${user.role}</a></td>
			</tr>
		</c:forEach>
	</table>
	<h3><a href="/">Main page</a></h3>
</body>
</html>