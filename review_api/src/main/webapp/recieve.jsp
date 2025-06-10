<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- method : post 방식일 때는 11번줄처럼 setCha~ 해줘야함 -->
	<% request.setCharacterEncoding("utf-8"); %>
	<h2><%=request.getParameter("value1")%></h2>
</body>
</html>