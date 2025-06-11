<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 	<img alt="새똥이" src="img.jsp" width="200">
	<form>
		<label>생년월일을 입력하세요</label>
		<select name="year">
		<% for(int i = 1900; i <= 2025; i++) {
			if(i == 2025){
				out.println("<option selected>" + i + "</option>");
			}
			else {
				out.println("<option>" + i + "</option>");
				}
			}
		%>
		</select>
		<select name="month">
		<% for(int i = 1; i <= 12; i++) { %>
			<option><%=i %></option>
		<% } %>
		</select>
		<select name="date">
		
		<%
			for(int i = 1; i <= 31; i++){
				out.println("<option>" + i + "</option>"); 
			}
		%>
		</select>
		<button>전송</button>
	</form>
		<%
			String year = request.getParameter("year");
			if(year == null){
				return;
			}

			int y = Integer.parseInt(year);
			if(2025 - y < 20){
				out.println("<h3>성인이 아닙니다</h3>");
			}
			else{
				out.println("<h3>성인입니다</h3>");
			}
		%>
</body>
</html>