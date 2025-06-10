<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="util.hikariCPUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC - Model1 방식</title>
<style>
	table, th, td {border : 1px solid }
	table {border-collapse : collapse; margin:12px auto }
	th, td {padding : 16px }
</style>
</head>
<body>
<%
	DataSource source = hikariCPUtil.getDataSource();
	Connection connection = source.getConnection();
	PreparedStatement pstmt = connection.prepareStatement("select * from tbl_review");
	ResultSet rs = pstmt.executeQuery();
%>
<table>
	<tr>
		<th>내용</th>
		<th>작성자</th>
		<th>작성일시</th>
		<th>별점</th>
	</tr>
	<% while(rs.next()) {%>
	<tr>
		<td><a href="review.jsp?rno=<%=rs.getLong("rno")%>"><%=rs.getString("content") %></a></td>
		<td><%=rs.getString("writer") %></td>
		<td><%=rs.getString("regdate") %></td>
		<td><%=rs.getString("rating") %></td>
	</tr>
	<% } %>
</table>
</body>

</html>