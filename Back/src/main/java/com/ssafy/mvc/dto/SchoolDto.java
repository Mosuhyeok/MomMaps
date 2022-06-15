package com.ssafy.mvc.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SchoolDto {
	private String code;
	private String name;
	private String level;
	private String personal;
	private String lat;
	private String lng;
}
