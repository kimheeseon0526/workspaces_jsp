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
		//scriptlet
		//자바 코드 작성		
		int num1 = Integer.parseInt(request.getParameter("value1"));
		int num2 = Integer.parseInt(request.getParameter("value2"));
	%>
	<!-- 수집한 파라미터의 각 계산 결과값만 출력 -->
	<p><%=num1 + num2 %></p>
	<p><%=num1 - num2 %></p>
	<p><%=num1 * num2 %></p>
	<p><%=num1 / num2 %></p>

</body>
</html>