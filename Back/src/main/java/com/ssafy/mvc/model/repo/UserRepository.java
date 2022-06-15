package com.ssafy.mvc.model.repo;

import java.sql.SQLException;
import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mvc.dto.*;

@Mapper
public interface UserRepository {
	void insert(UserDto user) throws SQLException;

	UserDto select(String id) throws SQLException;

	void delete(UserDto user) throws SQLException;

	void update(UserDto user) throws SQLException;

}
