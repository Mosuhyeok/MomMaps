package com.ssafy.mvc.dto;

import lombok.Data;

@Data
public class PostDto {
	private int id;
	private String author;
	private String title;
	private String content;
	private int view;			// 죄회수
	private int recommend;		// 추천수
	private String type;	// 게시판 타입
}
