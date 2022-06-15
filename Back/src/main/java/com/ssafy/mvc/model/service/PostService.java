package com.ssafy.mvc.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.mvc.dto.MyInfoWriteDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.ssafy.mvc.dto.CommentDto;
import com.ssafy.mvc.dto.PostCommentDto;
import com.ssafy.mvc.dto.PostDto;
import com.ssafy.mvc.model.repo.CommentRepository;
import com.ssafy.mvc.model.repo.PostRepository;
import com.ssafy.mvc.validation.Validation;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
	
	private final CommentRepository commentRepository;
	private final PostRepository postRepository;
	private final Validation validation;


	// 댓글수 조회
	public int getCommentCnt(int id) throws SQLException{
		return postRepository.getCommentCnt(id);
	}

	// 추천
	@Transactional
	public void recommend(int id) throws SQLException{
		PostDto postDto = postRepository.selectById(id);
		postDto.setRecommend(postDto.getRecommend()+1);
		postRepository.recommend(postDto.getRecommend(), postDto.getId());
	}


	public List<PostDto> selectAll(int page,String type) throws SQLException{
		return postRepository.selectAll(page,type);
	}
	
	
	public PostCommentDto selectPostAndComment(int id) throws SQLException {
		PostDto postDto = postRepository.selectById(id);
		List<CommentDto> list = commentRepository.selectAllByPostId(id);
		postDto.setView(postDto.getView()+1);
		postRepository.view(postDto);
		// 게시글 + 댓글을 합친걸 하나의 dto에 반환
		
		PostCommentDto postCommentDto = new PostCommentDto();
		postCommentDto.setPostDto(postDto);
		postCommentDto.setCommentDtos(list);
		return postCommentDto;
	}
	
	public PostDto selectById(int id) throws SQLException {
		PostDto postDto = postRepository.selectById(id);

		return postDto;
	}
	
	@Transactional
	public void insert(PostDto postDto) throws Exception {
		postRepository.insert(postDto);
	}
	
	@Transactional
	public void update(PostDto postDto) throws Exception {
		postRepository.update(postDto);
	}
	@Transactional
	public void delete(int postNum) throws Exception {
		postRepository.delete(postNum);
	}
	// 이름으로 검색
	public List<PostDto> searchByAuthor(String type,String author) throws SQLException{
		return postRepository.searchByAuthor(type,author);
	}
	// 제목으로 검색
	public List<PostDto> searchByTitle(String type,String title) throws SQLException{
		return postRepository.searchByTitle(type,title);
	}

	// 내가 쓴 글 보기
	public List<MyInfoWriteDto> myWriteList(String author) throws SQLException{
		List<PostDto> list = postRepository.searchByAuthorAll(author);
		List<MyInfoWriteDto> myInfoWriteDtoList = new ArrayList<>();
		for(PostDto postDto: list){
			MyInfoWriteDto tmp = new MyInfoWriteDto();
			tmp.setPost_id(postDto.getId());
			tmp.setTitle(postDto.getTitle());
			myInfoWriteDtoList.add(tmp);
		}
		return myInfoWriteDtoList;
	}
	public int getBoardCnt(String type) throws SQLException{
		return postRepository.getBoardCnt(type);
	}

	public List<PostDto> bestPost(int page) throws SQLException{
		return postRepository.selectBest(page);
	}

	public int getBestBoardCnt() throws SQLException{
		return postRepository.getBestBoardCnt();
	}
}
