<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp" %>
</head>
<body>
	<div class="container">
		<form method = "post" enctype="multipart/form-data" id="uploadForm" >
			<div class="d-grid my-2">
				<label class="btn btn-info">파일 첨부<input type="file" name="f1" multiple class="d-none"></label>
			</div>
			<button class="btn btn-primary btn">파일 업로드</button>
		</form>
	</div>
	<script>
	$(function() {
		$("#uploadForm").submit(function() {
			event.preventDefault();
			const formData = new FormData(this);
			console.log(formData);
			
			$.ajax({
				url : '${cp}/upload',
				method : 'POST',
				data : formData,
				processData : false,	//data 를 queryString으로 쓰지 않음
				contentType : false, 	//원래는 multipart/form-data; 이후에 나오게 될 브라우저 정보 포함. 즉, 기본 브라우저 설정 따르는 옵션
				success : function(data){
					console.log(data);
					//확인용
					for(let a in data){
					$(".container").append("<p>" + data[a].origin + "</p>");
					}
				}
			})
		})
	})
	</script>
</body>
</html>