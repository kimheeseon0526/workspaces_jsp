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
            <form method="post">
            <div class="samll border-bottom border-3 border-primary p-0 pb-2"><a href="#" class="samll"><span class="text-primary">자유 게시판</span> 카테고리</a></div>
            <div class="small p-0 py-2 ">
                <input placeholder="title" class="form-control" name="title" id="title">
            </div>
            <div class="p-0 py-3 ps-1 small border-bottom border-1 border-muted">
                <textarea name="content" id="editor1" class="form-control resize-none"></textarea>
            </div>
			
			<div class="d-grid my-2 ">
				<div class="small my-1"><i class="fa-solid fa-paperclip"></i> 첨부파일</div>
				<label class="btn btn-info">파일을 첨부하시려면 클릭클릭!<input type="file" multiple class="d-none" id="f1"></label>
			</div>
			
            <div class="my-2">
                <button class="btn btn-secondary btn-sm"><i class="fa-solid fa-list-ul"></i> 목록</button>
                <div class="float-end">
                    <button class="btn btn-outline-primary btn-sm"><i class="fa-solid fa-share-nodes"></i> 글쓰기</button>
                </div>
            </div>
            <input type="hidden" name="id" value="${member.id}" />
            <input type="hidden" name="cno" value="2" />
            </form>
        </main>
    </div>

    <script>
	$(function() {

		//return true / false
		function validateFiles(files){
			const MAX_COUNT = 5;
			const MAX_FILE_SIZE = 10 * 1024 * 1024;	//10MB
			const MAX_TOTAL_SIZE = 50 * 1024 * 1024;	//50MB
			const BLOCK_EXT = ['exe','sh', 'jsp', 'java','class', 'html', 'js'];

			if(files.length > MAX_COUNT){
				alert('파일은 최대 5개만 업로드 가능');
				return false;
			}
			let totalSize = 0;	//파일 총량
			const isValid = files.every(f =>  {
				const ext = f.name.split(".").pop().toLowerCase(); //확장자 추출(확장자 삭제)
				totalSize += f.size;
				console.log(totalSize, f.size, ext)
				return !BLOCK_EXT.includes(ext) && f.size <= MAX_FILE_SIZE; //위에 선언했던 확장자 미포함
			}) && totalSize <= MAX_TOTAL_SIZE
			if(!isValid){
				alert('다음 파일 확장자는 업로드가 불가능하다[exe, sh, jsp, java, class, html, js] \n 또한 각 파일은 10MB이하 전체 합계는 50MB 이하여야한다 ')
			}
			return isValid;
		}
		
		$("#f1").change(function() { //값 변경시
		//$("#uploadForm").submit(function() {
			event.preventDefault();
			const formData = new FormData();
			
			const files = this.files;
			console.log(formData, files);
			
			for(let i = 0; i < files.length; i++){
				formData.append("f1", files[i]);
			}
			
			const valid =  validateFiles([...files])
			//배열로 만든 files 전달
			if(!valid){
				return;
			}
	
			
			
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

        $(function() {
            CKEDITOR.replace('editor1', {
                height : 400
            });
        });
    </script>
<%@ include file="../common/footer.jsp" %>
</body>
</html>