package com.ssafy.mvc.model.service;

import java.sql.SQLException;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.mvc.dto.UserDto;
import com.ssafy.mvc.model.repo.UserRepository;
@Service
@RequiredArgsConstructor
public class UserService  {
	
	private final UserRepository repo;

	private final BCryptPasswordEncoder passwordEncoder;


	@Transactional
	public void register(UserDto user) throws SQLException {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repo.insert(user);
	}
	

	public UserDto login(String id, String password) throws SQLException {
		UserDto user = repo.select(id);
		if(user != null && passwordEncoder.matches(password, user.getPassword())) {
			return  user;
		}
		return  null;
	}


	@Transactional
	public void deletAccount(UserDto user) throws SQLException {
		repo.delete(user);
	}
	

	@Transactional
	public void updateAccount(UserDto user) throws SQLException {
		System.out.println(user.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		System.out.println(user.getPassword());
		repo.update(user);
	}


	public UserDto findUser(String userId) throws SQLException {
		return repo.select(userId);
	}


}
