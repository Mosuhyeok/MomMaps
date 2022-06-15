package com.ssafy.mvc.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginDto {
    private String id;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String sidoCode;
    private String gugunCode;
    private String dongCode;
    private String token;
}
