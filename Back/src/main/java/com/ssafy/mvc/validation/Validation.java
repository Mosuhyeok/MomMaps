package com.ssafy.mvc.validation;

import org.springframework.stereotype.Component;

// 게시글,댓글 등록 할 때 로그인한 유저인지 확인

@Component
public class Validation {
	
	// 아이디를 기준으로 확인
	public void userValidationCheck(String userA, String userB) throws Exception {
		if(!userA.equals(userB)) throw new Exception("접근 할 수 없습니다");
	}
}
