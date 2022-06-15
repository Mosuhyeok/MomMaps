package com.ssafy.mvc.dto;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {
    private String email;
    private int value;
}
