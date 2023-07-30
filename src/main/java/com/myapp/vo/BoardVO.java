package com.myapp.vo;

import lombok.Data;

@Data
public class BoardVO {
	
	private int seq;
	private String title;
	private String content;
	private String id;
	private String registDate;
	private String modifyDate;
	private String deleteYn;
	private String noticeYn;
	private int viewCount;
	private int category;


	private int commentCount;

	private int rowNum;
	// 요청시 초기화
	private int pageNum = 1;
	// 한 페이지당 12개까지 표기
	private int pagePerNum = 12;
	// 페이지별 시작게시글 위치
	private int offset = 0;
	
}
