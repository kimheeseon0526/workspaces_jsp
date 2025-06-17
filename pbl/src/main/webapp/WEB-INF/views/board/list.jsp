<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<%@ include file="../common/head.jsp" %>
</head>
<body>
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/nav.jsp" %>
	<div class="container p-0">
	<main>
		<div class="clearfix py-0">
                <a href="write" class="btn btn-primary btn-sm float-end"><i class="fa-solid fa-pen-fancy"></i> 글쓰기</a>
            </div>
            <div class="list-group">
                <div class="list-group-item small">
                    <div class="row text-center aligin-items-center small text-muted">
                        <div class="col-1 small">글번호</div>
                        <div class="col-1 small">카테고리</div>
                        <div class="col text-start">제목</div>
                        <div class="col-1 small">작성일</div>
                        <div class="col-1 samll">조회수</div>
                    </div>
                </div>
                <a href="board_view.html" class="list-group-item list-group-item-action list-group-item-secondry">
                    <div class="row text-center aligin-items-center small text-muted">
                        <div class="col-1 small">1</div>
                        <div class="col-1 small">자유</div>
                        <div class="col text-start text-black">제목</div>
                        <div class="col-1 small"><span class="samll">25.06.13</span></div>
                        <div class="col-1 samll"><span class="samll">13</span></div>
                    </div>
                </a>
                <c:forEach items="${boards}" var="board">
                   <a href="view?bno=${board.bno}" class="list-group-item list-group-item-action">
	                   <div class="row text-center aligin-items-center small text-muted">
	                       <div class="col-1 small">${board.bno}</div>
	                       <div class="col-1 small">${board.cno}</div>
	                       <div class="col text-start text-black">${board.title} <span class="small text-danger fw-bold">1</span></div>
	                       <fmt:parseDate value="${board.regdate}" pattern="yyyy-MM-dd HH:mm:ss" var="parsedDate" />
	                       <fmt:formatDate value="${parsedDate}" pattern="yy.MM.dd" /> 
	                       <div class="col-1 samll"><span class="samll">${board.cnt}</span></div>
	                   </div>
                	</a>
                </c:forEach>
     
</main>
<%@ include file="../common/footer.jsp" %>
</body>
</html>