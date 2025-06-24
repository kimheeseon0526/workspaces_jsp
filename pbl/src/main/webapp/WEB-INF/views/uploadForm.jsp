<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="common/head.jsp" %>
</head>
<body>
	<div class="container">
			<div class="d-grid my-2 attach-area">
				<label class="btn btn-info">파일 첨부<input type="file" multiple class="d-none" id="f1"></label>
				<ul class="list-group my-3 attach-list">
				  <li class="list-group-item d-flex align-items-center justify-content-between"><span>First item</span><i class="fa-regular fa-circle-xmark float-end text-danger"></i></li>
				  <li class="list-group-item d-flex align-items-center justify-content-between"><span>Second item</span><i class="fa-regular fa-circle-xmark float-end text-danger"></i></li>
				  <li class="list-group-item d-flex align-items-center justify-content-between"><span>Third item</span><i class="fa-regular fa-circle-xmark float-end text-danger"></i></li>
				</ul>
				
				<div class="row justify-content-around w-75 mx-auto attach-thumb">
					<div class="my-2 col-sm-4 col-lg-2"><div style="height: 150px" class="my-2 bg-primary" ><i class="fa-regular fa-circle-xmark float-end text-danger m-2"></i></div></div>                                                 
					<div class="my-2 col-sm-4 col-lg-2"><div style="height: 150px" class=" my-2 bg-info" ><i class="fa-regular fa-circle-xmark float-end text-danger m-2"></i></div></div>
					<div class="my-2 col-sm-4 col-lg-2"><div style="height: 150px" class=" my-2 bg-warning" ><i class="fa-regular fa-circle-xmark float-end text-danger m-2"></i></div></div>
					<div class="my-2 col-sm-4 col-lg-2"><div style="height: 150px" class=" my-2 bg-secondary" ><i class="fa-regular fa-circle-xmark float-end text-danger m-2"></i></div></div>
					<div class="my-2 col-sm-4 col-lg-2"><div style="height: 150px" class=" my-2 bg-success" ><i class="fa-regular fa-circle-xmark float-end text-danger m-2"></i></div></div>
				</div>
			</div>
			
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
					let str = "";
					for(let a of data){
					//$(".container").append("<p>" + data[a].origin + "x</p>");
					str += `<li class="list-group-item d-flex align-items-center justify-content-between"
						data-uuid="\${a.uuid}"
						data-origin="\${a.origin}"
						data-image="\${a.image}"
						data-path="\${a.path}"
						data-odr="\${a.odr}"				
					>

						<a href="${cp}/download">\${a.origin}</a>
						<i class="fa-regular fa-circle-xmark float-end text-danger"></i>
						</li>`;
					}
					console.log(str);
					$(".attach-list").html(str);
					//이미지인 경우와 아닌 경우
					
				}
			})
		})
	})
	</script>
</body>
</html>