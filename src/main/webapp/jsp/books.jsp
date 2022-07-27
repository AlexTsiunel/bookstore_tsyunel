<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Books</title>
</head>
<body>
	<table>
		<th>#</th>
		<th>Id</th>
		<th>Title</th>
		<c:forEach items="${books}" var="book" varStatus="counter">
			<tr>
				<td>${counter.count}</td>
				<td><a href="controller?command=book&id=${book.id}">${book.id}</a></td>
				<td><a href="controller?command=book&id=${book.id}">${book.title}</a></td>
			</tr>
		</c:forEach>
	</table>
	<h3><a href="/bookstore">Main page</a></h3>
</body>
</html>