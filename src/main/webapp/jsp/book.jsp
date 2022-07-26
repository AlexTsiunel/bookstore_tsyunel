<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Book</title>
  </head>
  <body>
    <h1>The Book</h1>
    <h3>Id</h3>
    <p>${book.id}</p>
    <h3>Title</h3>
    <p>${book.title}</p>
    <h3><a href="books">All books</a></h3>
  </body>
</html>
