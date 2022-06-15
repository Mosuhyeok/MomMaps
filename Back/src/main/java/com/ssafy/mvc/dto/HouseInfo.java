package com.ssafy.mvc.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HouseInfo {
	private String aptCode;
	private String apartmentName;
	private String dongCode;
	private String dong;
	private int buildYear;
	private String lat;
	private String lng;
}
