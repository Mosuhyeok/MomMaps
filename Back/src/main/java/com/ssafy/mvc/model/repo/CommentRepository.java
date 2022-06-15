package com.ssafy.mvc.model.repo;

import java.util.List;

import com.ssafy.mvc.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentRepository {
	int insert(CommentDto comment);
	int delete(int id);
	int update(CommentDto comment);
	CommentDto searchById(int id);
	List<CommentDto> selectAllByPostId(int id);
}

