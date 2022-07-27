<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
  <head>
    <title>User</title>
  </head>
  <body>
    <h1>The User</h1>
    <h3>Id</h3>
    <p>${user.id}</p>
    <h3>First Name</h3>
    <p>${user.firstName}</p>
    <h3>Last Name</h3>
    <p>${user.lastName}</p>
    <h3>Email</h3>
    <p>${user.email}</p>
    <h3>Role</h3>
    <p>${user.role}</p>
    <h3><a href="controller?command=users">All users</a></h3>
  </body>
</html>
