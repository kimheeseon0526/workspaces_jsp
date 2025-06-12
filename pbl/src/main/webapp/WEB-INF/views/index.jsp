<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>index</h1>
<c:if test="${empty member}">
<a href="member/register">회원가입</a>
<a href="member/login">로그인</a>
</c:if>

<c:if test="${not empty member}">
<span>${member.name}님 어서오고</span>
<a href="member/logout">로그아웃</a>
</c:if>
</body>
</html>