<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	System.out.println(request.getParameter("v"));
	request.getRequestDispatcher("index.jsp").forward(request, response);
	//url이 forward.jsp이나 화면은 index.jsp 내용이 보임
	//request:forward response : index
	//forward : 응답 바꿔치기!
	
	//forward : 내 응답 가져가지 않고 바꿈
	//include : 내 응답 가져가기
	//redirect
	%>
</body>
</html>