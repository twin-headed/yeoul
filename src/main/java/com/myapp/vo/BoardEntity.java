package com.myapp.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board")
public class BoardEntity {
	@Id
	@Column
	private Long seq;

	@Column
	private String title;

	@Column
	private String content;

	@Column
	private String id;

	@Column(name = "registDate")
	private String registDate;

	@Column(name = "modifyDate")
	private String modifyDate;

	@Column(name = "deleteYn")
	private String deleteYn;
}
