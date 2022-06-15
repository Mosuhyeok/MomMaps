package com.ssafy.mvc.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookDto {
	private String storename;
	private String lat;
	private String lng;
	private String address;
	private String rest;
}
