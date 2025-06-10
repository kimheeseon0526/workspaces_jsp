<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="util.hikariCPUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC - Model2 방식</title>
<style>
	table, th, td {border : 1px solid }
	table {border-collapse : collapse; margin:12px auto }
	th, td {padding : 16px }
</style>
</head>
<body>
<h1>MVC로 구성한 화면</h1>
<table>
	<tr>
		<th>내용</th>
		<th>작성자</th>
		<th>작성일시</th>
		<th>별점</th>
	</tr>
	<c:forEach items="${reviews}" var="r">
	<tr>
		<td><a href="review.jsp?rno=${r.rno}">${r.content}</a></td>
		<td>${r.writer}</td>
		<td>${r.regdate}</td>
		<td>${r.rating}</td>
	</tr>
	</c:forEach>
</table>
</body>

</html>