package com.ssafy.mvc.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DealInfoDto {
    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private String area;    // 평수
    private String floor;   //층수
    private String dealAmount;
    private String apartmentName;
    private String dongCode;
    private String dong;
    private String lng;
    private String lat;
    private int buildYear;
    private String aptCode;
}