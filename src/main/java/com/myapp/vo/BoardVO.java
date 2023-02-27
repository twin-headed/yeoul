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
	private int rowNum;
	
	private int pageNum = 1;
	private int pagePerNum = 12;
	private int offset = 0;
	
}
