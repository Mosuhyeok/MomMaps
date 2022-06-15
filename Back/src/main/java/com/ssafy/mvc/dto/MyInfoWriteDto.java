package com.ssafy.mvc.dto;

import lombok.Data;


// 내가 쓴 글 목록
@Data
public class MyInfoWriteDto {

    private int post_id;
    private String title;
}
