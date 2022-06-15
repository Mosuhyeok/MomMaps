package com.ssafy.mvc.dto;

import lombok.Data;

@Data
public class InterestDto {

    private int id;
    private String aptCode;
    private String apartmentName;
    private String dealAmount;
    private int dealYear;
    private int dealMonth;
    private int dealDay;
    private String area;
    private String floor;
    private String userId;
}
