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
    text-align: center;
}

.fz_board tbody td {
    border-bottom: 1px solid #e6e6e6;
    text-align: center;
    height: 35px;
    margin: 10px;
    color: #333;
}

.table-hover tbody tr:hover td {
    background: #fff2e6;
}

.page-link {
	font-size:11px;
    font-family:"맑은 고딕","Malgun Gothic";
}

.active > .page-link {
}

input {
margin-top:0px;
}

.btn_search {
    background: url(/resources/img/sprites_btn.png) no-repeat;
    width: 36px;
    height: 27px;
    line-height: 27px;
    text-indent: -9999px;
    border: 0;
    cursor: pointer;
    padding: 0;
}

select, option {

}

</style>
<script src="/resources/js/jquery-3.1.1.js"></script>
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
				<div style="width: 23%; float: left; margin-right:0.5%">
					<div>여울서버는 어떤곳인가요?</div>
					<div>
						<br>안녕하세요.여울GM입니다.<br> 경험치 보정배율 서버입니다.<br> 서버는 24시간
						상시 ON입니다.<br> 오래 운영할테니 많이 찾아주세요.<br> <br>감사합니다.<br>
						<br> <br>서버오픈일 2020.12.24
					</div>
					<h2>
						경험치 배율: <br>1배 -> 보정배율 변경<br> <br> <br> 서버
						구현률: 100%<br> 서버 안정성: 100%<br> <br> <br>Made
						by 아기사자
					</h2>
				</div>
				<div class="container" style="width: 75%; float:left; margin-right:0%; padding:0px; background-color:white">
					<table class="table table-hover fz_board">
						<colgroup>
					        <col width="10%">
					        <col width="50%">
					        <col width="20%">
					        <col width="10%">
					        <col width="10%">
					    </colgroup>
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>날짜</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody id="list">
						<c:forEach var="vo" items="${list}">
							<tr>
								<td>${vo.seq}</td>
								<td><a href="/board/view/${vo.seq}">${vo.title}</a></td>
								<td>${vo.id}</td>
								<td>${vo.registDate}</td>
								<td>${vo.deleteYn}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					
					<br>
					
					<div>
						<ul class="pagination justify-content-center" id="paging">
						  </ul>
					</div>
					
					<div style="margin-bottom:10px;">
						<select id="select" style="margin-left:25%; height:24.5px; width:70px;">
						  <option value="1" selected>제목</option>
						  <option value="2">작성자</option>
						  <option value="3">내용</option>
						  <option value="4">제목+내용</option>
						</select>
						<input id="input" type="text" size="15" maxlength="20" style="justify-content: center !important;">
						<input type="submit" value="검색" class="btn_search" alt="검색" onclick="selectBoardList(1)">	
					</div>
			    
				</div>
			</div>
		</div>
		<div style="text-align: center;">
				<구버전 프리 바람의나라-Renewal By Yeoulserver>
			</div>
	</div>
	<script>
		$(document).ready(function(){
			selectBoardList(1);
		})
		
		function selectBoardList(page) {
			
			var data = {
					pageNum : page? page : 1,
			}
			
			if(!$("#input").val()) {
				
			}else {
				switch ($("#select").val()) {
					case "1" : data.title = $("#input").val();
					break;
					case "2" : data.id = $("#input").val();
					break;
					case "3" : data.content = $("#input").val();
					break;
					case "4" : data.title = $("#input").val(), data.content = $("#input").val();
					break;
				}	
			}
			
			
			$.ajax({
				type : "POST",
				data : JSON.stringify(data),
				contentType: 'application/json',
				dataType: 'JSON',
				url : "/board/list",
				success : function(response) {
					console.log(response);
					$("#list").empty();
					$("#paging").empty();
					
					/*
					$.each(response.list, function(index, item) {
						var listHtml = '<tr>'
						$.each (function() {
							listHtml = listHtml + '<td>' + item.seq + '</td>'
							listHtml = listHtml + '<td>' + item.content + '</td>'
							listHtml = listHtml + '<td>' + item.id + '</td>'
							listHtml = listHtml + '<td>' + item.registDate + '</td>'
						}); 
						listHtml = listHtml + '</tr>';
						$("#list").append(listHtml);
					});	
					*/
					
					if(response.list.length > 0) {
						
						for(var i=0; i<response.list.length; i++) {
							var listHtml = '<tr>'
							listHtml = listHtml + '<td>' + response.list[i].rowNum + '</td>'
							listHtml = listHtml + '<td>' + '<a href="/board/view/'+response.list[i].seq+'">' + response.list[i].title + '</td>'
							listHtml = listHtml + '<td>' + response.list[i].id + '</td>'
							listHtml = listHtml + '<td>' + response.list[i].registDate + '</td>'
							listHtml = listHtml + '<td>' + response.list[i].deleteYn + '</td>'
							listHtml = listHtml + '</tr>';
							$("#list").append(listHtml);
						}
						
						for(var i=response.startPage; i<=response.endPage; i++) {
							
							if(i == response.pageNum) {
								$("#paging").append('<li class="page-item active"><a class="page-link" href="javascript:void(0)" onclick="selectBoardList('+i+')">'+i+'</a></li>');
							}else {
								$("#paging").append('<li class="page-item"><a class="page-link" href="javascript:void(0)" onclick="selectBoardList('+i+')">'+i+'</a></li>');	
							}
							
							
							// 마지막반복시 페이징처리추가
							if(i == response.endPage) {
								
								if(response.pageNum > 10) {
									$('<li class="page-item"><a class="page-link" href="#" onclick="selectBoardList('+ (response.startPage-1) +')">이전</a></li>').prependTo("#paging");
									$('<li class="page-item"><a class="page-link" href="#" onclick="selectBoardList(1)">&lt;&lt;</a></li>').prependTo("#paging");
								}
								
								if(response.totalPage > 10) {
									if((parseInt(response.endBlock*10) > response.pageNum) && (response.pageNum > parseInt(response.endBlock*10) - 10)) {
										
									}else {
										$('<li class="page-item"><a class="page-link" href="#" onclick="selectBoardList('+ (response.startPage+10) +')">다음</a></li>').appendTo("#paging");
										$('<li class="page-item"><a class="page-link" href="#" onclick="selectBoardList('+ response.totalPage +')">&gt;&gt;</a></li>').appendTo("#paging");
									}
								
								}
							}
						}
						
						$("#paging").append('<li class="page-item" style="margin-left:1%;"><a class="page-link" href="/board/write">글쓰기</a></li>');
						
					}else {
						var listHtml = '<tr>'
						listHtml = listHtml + '<td colspan="5" style="padding: 50px 0 60px 0;">게시물이 없습니다.</td>'
						listHtml = listHtml + '</tr>';
						$("#list").append(listHtml);
						$("#paging").append('<li class="page-item" style="margin-left:80%;"><a class="page-link" href="/board/write">글쓰기</a></li>');
					}
					
					
					
					
					/*
					if(response.totalBlock < 11) { // 전체 블록이 10개 이하
						
					}else{
						if(response.pageNum > (response.totalBlock - 10) ){ // 현재 페이지가 마지막 블록대역에 속했을때
							$("#paging").prepend('<li class="page-item"><a class="page-link" href="#" onclick="selectBoardList(1)>&lt;&lt;</a></li>');
							$("#paging").prepend('<li class="page-item"><a class="page-link" href="#" onclick="selectBoardList('+(response.pageNum-1)+')>Previous</a></li>');
						}else if(response.pageNum < 11){	// 현재 페이지가 11페이지 미만일때
							$('<li class="page-item"><a class="page-link" href="#" onclick="selectBoardList('+(response.pageNum+1)+')">Next</a></li>').appendTo("#paging");
							$('<li class="page-item"><a class="page-link" href="#" onclick="selectBoardList('+response.totalCnt+')">&gt;&gt;</a></li>').appendTo("#paging");
						}else { // 나머지 케이스
							$("#paging").prepend('<li class="page-item"><a class="page-link" href="#" onclick="selectBoardList(1)>&lt;&lt;</a></li>');
							$("#paging").prepend('<li class="page-item"><a class="page-link" href="#" onclick="selectBoardList('+(response.pageNum-1)+')>Previous</a></li>');
							$("#paging").appendTo('<li class="page-item"><a class="page-link" href="#" onclick="selectBoardList('+(response.pageNum+1)+')>Next</a></li>');
							$("#paging").appendTo('<li class="page-item"><a class="page-link" href="#" onclick="selectBoardList('+response.totalCnt+')">&gt;&gt;</a></li>');
						}
					}
					*/
				},
				error: function() {
					alert("조회실패!");
				}
			});
		}
	
		$(".logo").on("click",function(){
			location.href="/";			
		});
		
	</script>
</body>
</html>