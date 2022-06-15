package com.ssafy.mvc.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.ssafy.mvc.dto.LoginCheckDto;
import com.ssafy.mvc.dto.LoginDto;
import com.ssafy.mvc.dto.UserLoginDto;
import com.ssafy.mvc.jwt.JwtTokenProvider;
import com.ssafy.mvc.model.service.UserService;
import com.ssafy.mvc.validation.Validation;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ssafy.mvc.dto.UserDto;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserRestController {

	private final UserService userService;
	private final  Validation validation;
	private final JwtTokenProvider jwtTokenProvider;


	@PutMapping("/user")
	public UserDto update(@RequestBody UserDto userDto) throws SQLException {
		System.out.println("update : " +userDto);
		userService.updateAccount(userDto);
		return userDto;
	}

	@DeleteMapping("/user")
	public UserDto delete(HttpSession session, Model model) throws SQLException {
		UserDto userDto = (UserDto) session.getAttribute("user");
		userService.deletAccount(userDto);
		return userDto;
	}

	@PostMapping("/user")
	public UserDto insert(@RequestBody UserDto userDto) throws SQLException {
		userService.register(userDto);
		return userDto;
	}
	@GetMapping("/myInfo")
	public ResponseEntity<?> myInfo(HttpSession session, Model model) {
		UserDto user = (UserDto) session.getAttribute("user");
		return new ResponseEntity<UserDto>(user, HttpStatus.OK);
	}
	@PostMapping("/login")
	public UserLoginDto login(@RequestBody LoginCheckDto loginCheckDto) throws SQLException {

		UserDto user = userService.login(loginCheckDto.getId(),loginCheckDto.getPassword());
		if(user!= null){
			List<String> roles = new ArrayList<>();
			roles.add(user.getRole());
			UserLoginDto dto =  UserLoginDto.builder()
					.id(user.getId())
					.password(user.getPassword())
					.name(user.getUsername())
					.email(user.getEmail())
					.phone(user.getPhone())
					.role(user.getRole())
					.sidoCode(user.getSidoCode())
					.gugunCode(user.getGugunCode())
					.dongCode(user.getDongCode())
					.token(jwtTokenProvider.createToken(user.getId(),roles))
					.build();
			return dto;
		}
		throw  new IllegalArgumentException("해당 하는 아이디가 없습니다");
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<?> findUser(@PathVariable String id) throws SQLException{
		UserDto user = userService.findUser(id);
		return new ResponseEntity<UserDto>(user,HttpStatus.OK);
	}
}
