package com.ssafy.mvc.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HouseAllDto {
	private int buildYear;
	private String dong;
	private String dongCode;
	private String aptName;
	private String lng;
	private String lat;
	private String dealAmount;
	private int dealYear;
	private String area;
}
