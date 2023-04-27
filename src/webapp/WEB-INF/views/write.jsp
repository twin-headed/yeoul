<%@page import="org.springframework.ui.Model"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<link rel="stylesheet" href="/resources/css/bootstrap.css">
<link rel="stylesheet" type="text/css" media="screen, projection"
	href="/resources/css/menu.css">
<link rel="stylesheet" type="text/css" media="screen, projection"
	href="/resources/css/style.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1250">
<style type="text/css">
div.wrap1 .wrap2 {
	/* only with left panel- background-image: url(img/bg1.gif); */
	/* right and left panel */
	background-image: url(/resources/img/bg2.gif);
	/* only with right panel - background-image: url(img/bg3.gif); */
}

.jumbotron {
	background-size: cover; =
	color: white;
}

*, *::before, *::after {
    box-sizing: initial; 
}
.fz_board {
    border-collapse: collapse;
    width: 90%;
    table-layout: fixed;
    background-color: #FFF;
    border-top: 2px solid #ddd;
    /*
    border-left: 1px solid #e6e6e6; 
    border-right: 1px solid #e6e6e6;
    */
    display: table;
    font-size:11px;
    font-family:"맑은 고딕","Malgun Gothic";
    margin:0 auto;
    margin-top:3%;
}

.fz_board thead {
    background: linear-gradient(to bottom, #fefefe 0%,#f0f0f0 100%);
    filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#fefefe', endColorstr='#f0f0f0',GradientType=0 );
    border-left: 1px solid #DDD;
    border-right: 1px solid #DDD;
}

.table-hover tbody tr:hover td {
    background: #fff2e6;
}

.page-link {
	font-size:11px;
    font-family:"맑은 고딕","Malgun Gothic";
}

</style>
<script src="/resources/js/jquery-3.1.1.js"></script>
<script src="/resources/summerNote/summernote-lite.js"></script>
<script src="/resources/summerNote/summernote-ko-KR.js"></script>
<link rel="stylesheet" href="/resources/summerNote/summernote-lite.css">
</head>
<body>
	<div class="wrap1">
		<div class="wrap2">
			<div class="logo" style="cursor: pointer;">
				<div style="display: inline; float: left;">
					여울서버
					<div class="slogan">여러분을 초대합니다.</div>
				</div>
				<div style="display: inline; float: right"></div>
			</div>

			<div id="menu">
				<div class="menu" style="margin-top: -16px;">
					<ul>
						<li></li>
						<li><img src="/resources/img/divider2.png" alt=""></li>
						<li></li>
						<li><img src="/resources/img/divider2.png" alt=""></li>
						<li><span class="hlavny_"><a
								href="https://t.me/yeoulbaramchat">커뮤니티(단톡방)</a></span></li>
						<li><img src="/resources/img/divider2.png" alt=""></li>
						<li></li>
						<li><img src="/resources/img/divider2.png" alt=""></li>
						<li><span class="hlavny_"><a
								href="https://www.mediafire.com/file/jncheucvpbyfs8t/%255B21.12.10%255D_Yeoulserver.zip/file">자료실(다운로드)
							</a></span></li>
						<li><img src="/resources/img/divider2.png" alt=""></li>
						<li></li>
						<li><img src="/resources/img/divider2.png" alt=""></li>
						<li><span class="hlavny_"><a href="/board/list">사용중지게시판
							</a></span></li>
						<li><img src="/resources/img/divider2.png" alt=""></li>
						<li class="active"></li>
					</ul>
				</div>
				<div class="container" style="width: 99%; float:left; margin-right:0%; padding:5px; background-color:white">
					<label for="inputName">제목</label>
					<input type="text" id="title" style="width:70%; margin-left:5px; margin-bottom:10px; margin-right:15%;">
					<button type="button" class="btn btn-dark btn-sm" id="save">저장</button>
					<textarea class="summernote" name="editordata"></textarea>
				</div>
			</div>
		</div>
		<div style="text-align: center;">
				<구버전 프리 바람의나라-Renewal By Yeoulserver>
			</div>
	</div>
	<script>
		$(document).ready(function() {
			  $('.summernote').summernote({
				  height: 350,
				  lang: "ko-KR",
		            callbacks: {	//여기 부분이 이미지를 첨부하는 부분
						onImageUpload : function(files) {
							uploadSummernoteImageFile(files[0],this);
						}
					}
			  });
		});
		
		function uploadSummernoteImageFile(file, editor) {
			var data = new FormData();
			data.append("file", file);
			$.ajax({
				data : data,
				type : "POST",
				url : "/upload",
				contentType : false,
				processData : false,
				success : function(data) {
						console.log(data);
	          	            //항상 업로드된 파일의 url이 있어야 한다.
	          	            $(editor).summernote('insertImage', data,'file');
	          	        
				 }
				,error:function(request,status,error, data){
	          	            alert("Error");
	       	        }
			});
		}
	
		
		$(".logo").on("click",function(){
			location.href="/";			
		});
		
		$("#save").on("click",function(){
			
				if(!confirm("저장ㄱ?")) {
					return false;	
				}
				
				var data = {
					title: $("#title").val(),
					content: $(".summernote").val()
				}
				
				$.ajax({
					data : JSON.stringify(data),
					type : "POST",
					url : "/board/insert",
					contentType: 'application/json; charset=utf-8',
					success : function(response) {
						            alert("저장!");							
					 }
					,error:function(request,status,error, data){
		          	            alert("저장실패!");
		       	     }
				});
				
				
		});
		
	</script>
</body>
</html>