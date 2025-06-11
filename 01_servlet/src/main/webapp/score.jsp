<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form>
		<input name="kor">
		<input name="eng">
		<input name="mat">
		<button>계산하기</button>
		
		<%
		//parse로 인해 500 error 발생 -> 값을 문자열로 입력 받고 parsing 할 때 값이 null인 경우 처리해야됨(if문 사용)
			String kor = request.getParameter("kor");
			String eng = request.getParameter("eng");
			String mat = request.getParameter("mat");
			
			if(kor != null && eng != null && mat != null){
				int total =Integer.parseInt(kor) +Integer.parseInt(eng) + Integer.parseInt(mat);
				double avg = total / 3.0;
			
		%>
			<p><%=total %></p>
			<p><%=avg %></p>
			
		<%
			}
		%>

		
	</form>
</body>
</html>