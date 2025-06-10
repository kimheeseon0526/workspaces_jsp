<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!--  파라미터를 입력받을 폼 작성 -->
	<form action=calc_result.jsp method="get">
		<input name="value1">
		<input name="value2">
		<button>계산하기</button>
		<button formaction="calcServ">서블릿으로</button>
	</form>
	<h3><%=request.getParameter("v")%></h3>
</body>
</html>