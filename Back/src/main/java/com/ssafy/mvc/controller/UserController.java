package com.ssafy.mvc.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.ssafy.mvc.model.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.mvc.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}

	
	@GetMapping("/join")
	public String joinPage()  {
		return "join";
	}
	@PostMapping("/join")
	public String join(@RequestBody UserDto userDto) throws SQLException {
		userService.register(userDto);
		return "join";
	}


	
	// 로그아웃 페이지로 가기
	// 이러면 루트페이지로 가는지 아닌지 확인 불가 체크
	@GetMapping("/logout")
	public String logoutPage(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	
	// 실제 로그아웃 로직
	@PostMapping("/logout")
	public String logoutAction(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
}
