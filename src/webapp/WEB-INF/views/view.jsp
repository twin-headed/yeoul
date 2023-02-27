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
	background-color: white;
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

#bo_v_title {
    
    border-bottom: 1px solid #c8c8c6;
    background: #f8f8f8;
    padding: 18px 15px;
    font-size: 18px;
    font-weight: 700;
    font-family: "맑은 고딕","Malgun Gothic";
    color: #333;
    
}

#bo_v_info {
    padding: 0px;
    border-bottom: 1px solid #ddd;
    *zoom: 1;
    background: #fff;
}

#bo_v_info span {
    line-height: 40px;
    height: 40px;
}

.fl {
	float: left;
	/* padding-left: 15px; */
	width:30%;
}

.fr {
	padding-right:15px;
	float:right;
	
}

.bo_v_date .bo_v_hit .bo_v_comment{
	font-size: 11px;
    color: #333;
}

.bar {
    color: #aaa;
    margin: 0 10px;
    font-size: 11px;
}

#bo_v_info:after {
    content: "";
    display: block;
    clear: both;
}

#bo_vc .bo_vc_top {
    border-top: 2px solid #bdbdbd;
    background: #f9f9f9;
    letter-spacing: -1px;
    color: #777;
    padding: 12px 0px;
}

#bo_vc article {
    padding: 20px;
    border-top: 1px solid #e3e3e3;
}

.bo_vc_hdinfo {
    display: inline-block;
    color: #aaa;
    font-family: tahoma;
    font-size: 10px;
    position: relative;
    top: 1px;
}

#bo_vc .bo_vc_content {
    padding: 10px 0;
    font-family: "맑은 고딕","Malgun Gothic";
    color: #333;
}

#commenter {
	font-weight: 700;
	font-family: "맑은 고딕","Malgun Gothic";
    color: #333;
}

.bo_vc_act {
	background: url(/resources/img/comment.gif) no-repeat;
}

.comment_form .write_body {
    padding: 9px;
    border: 1px solid #e3e3e3;
    background: #fafafa;
}

textarea#wr_content {
    border: 1px solid #ccc;
}

.no_editor .bo_vc_wr_content {
    width: 98%;
    height: 100px !important;
    padding: 8px;
    line-height: 22px;
}

.no_editor .placeholder {
    display: block;
    opacity:1;
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
				<div style="width:100%;">
					<header>
        				<h1 id="bo_v_title">${vo.title}</h1>
					</header>
					<section id="bo_v_info">
						<div class="fl">
							<span class="bo_v_user">
								<span class="sound_only" style="margin-left:10px;">작성자 : ${vo.id}</span>
							</span>
						</div>
						<div class="fr">
							<span class="bo_v_date"><span class="sound_only">작성일</span><strong>22-10-18 12:06</strong></span> <span class="bar">|</span>
							<span class="bo_v_hit"> 조회 <strong>98</strong></span> <span class="bar">|</span>
							<span class="bo_v_comment"> 댓글 <strong>1</strong></span>
						</div>
					</section>
					<div style="height:fit-content; padding:2%; min-height:419px;">
						${vo.content}
					</div>
					<section id="bo_vc">
						<div class="bo_vc_top">
							<h2 style="margin-left:10px;">댓글 목록 <em>3</em></h2>
						</div>
						
						<article>
							<header style="z-index:4">
								<span class="bo_vc_symbol_target" id="c_5124"></span>
							<div class="f_wrap">
								<div class="fl">
									<span class="bo_vc_hdinfo"> <span id="commenter" style="margin-right:10px;">아기사자</span> <time datetime="2022-11-07T14:22:00+09:00">22-11-07 14:22</time></span>
								</div>
							</div>
							</header>
							
							<div class="bo_vc_content"><br>클라이언트만 있으면 맵 열수 있습니다 버전은 좀 낮아야해요 지금 본섭 클라이언트로 사용하시면 안열립니다<br>그리고 너무 높은 클라이언트 버전에서 만들어진 맵을 열면 안보이거나 튕길 수도 있슴다</div>
								<footer>
						            <ul class="bo_vc_act">
						                <a href="javascript:void(0)" style="text-decoration:none; color: #999 !important;">답글달기</a>                                            
						             </ul>
						        </footer>
						</article>
						<article>
							<header style="z-index:4">
								<span class="bo_vc_symbol_target" id="c_5124"></span>
							<div class="f_wrap">
								<div class="fl">
									<span class="bo_vc_hdinfo"> <span id="commenter" style="margin-right:10px;">아기사자</span> <time datetime="2022-11-07T14:22:00+09:00">22-11-07 14:22</time></span>
								</div>
							</div>
							</header>
							
							<div class="bo_vc_content"><br>클라이언트만 있으면 맵 열수 있습니다 버전은 좀 낮아야해요 지금 본섭 클라이언트로 사용하시면 안열립니다<br>그리고 너무 높은 클라이언트 버전에서 만들어진 맵을 열면 안보이거나 튕길 수도 있슴다</div>
								<footer>
						            <ul class="bo_vc_act">
						                <a href="javascript:void(0)" style="text-decoration:none; color: #999 !important;">답글달기</a>                                            
						             </ul>
						        </footer>
						</article>
						
						
						<div style="padding:9 9 30 9px; background:#fafafa; border:1px solid #e3e3e3;">
							<div class="write_body">
								<div class="no_editor">
								<span class="placeholder placeholder_textarea">
									<textarea name="wr_content" id="wr_content" class="bo_vc_wr_content"></textarea>
								</span>
								</div>
								<div class="f_wrap">
									<div class="fl">
										<span class="checkbox check-box"><input type="checkbox" id="wr_secret" name="wr_secret" value="secret"> <label for="wr_secret" class="wr_secret_label" style="font-size: 11px;">비밀 댓글</label></span>
									</div>
									<div class="fr">
										<input type="image" src="https://www.baraminside.com/skin/board/fz_basic_pc/img/btn_submit_comment.gif" alt="댓글등록" title="댓글등록">
									</div>
								</div>
							</div>
						</div>
				
					</section>
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
	          	                console.log("img upload Success");
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