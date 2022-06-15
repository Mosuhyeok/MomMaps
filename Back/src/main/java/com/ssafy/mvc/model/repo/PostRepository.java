package com.ssafy.mvc.model.repo;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.mvc.dto.MyInfoWriteDto;
import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mvc.dto.PostDto;

@Mapper
public interface PostRepository {
	
	
	PostDto selectById(int id) throws SQLException;
	List<PostDto> selectAll(int page, String type) throws SQLException;
	void insert(PostDto postDto) throws SQLException;
	void update(PostDto postDto) throws SQLException;
	void delete(int id) throws SQLException;
	List<PostDto> searchByTitle(String type,String title) throws SQLException;
	List<PostDto> searchByAuthor(String type,String author) throws SQLException;
	int getBoardCnt(String type) throws SQLException;
	void view(PostDto postDto) throws SQLException;	// 조회수
	void recommend(int recommend,int id) throws SQLException;
	int getCommentCnt(int id) throws SQLException;	// 댓글수

	List<PostDto> searchByAuthorAll(String author) throws SQLException;


	// 베스트 게시글
	List<PostDto> selectBest(int page) throws SQLException;

	// 베스트 게시글 갯수
	int getBestBoardCnt() throws SQLException;

}
