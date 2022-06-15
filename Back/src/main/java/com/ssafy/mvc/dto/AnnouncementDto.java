package com.ssafy.mvc.dto;

import lombok.Data;

@Data
public class AnnouncementDto {
    private int id;
    private String author;
    private String title;
    private String content;
}
