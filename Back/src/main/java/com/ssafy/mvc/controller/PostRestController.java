package com.ssafy.mvc.controller;

import static org.hamcrest.CoreMatchers.nullValue;

import javax.servlet.http.HttpSession;

import com.ssafy.mvc.dto.*;
import com.ssafy.mvc.model.service.UserService;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.ssafy.mvc.model.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class PostRestController {
	private final UserService userService;
	private final PostService postService;

	// 댓글수 조회
	@GetMapping("/post/commentCnt/{id}")
	public ResponseEntity<?> getCommentCnt(@PathVariable int id) throws SQLException{
		int num = postService.getCommentCnt(id);
		return new ResponseEntity<>(num,HttpStatus.OK);
	}
	@PostMapping("/post/recommend/{id}")
	public ResponseEntity<?> recommend(@PathVariable int id) throws SQLException {
		postService.recommend(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/post/search/{type}")
	public ResponseEntity<?> post(@RequestParam("page") int page ,@PathVariable String type) throws SQLException {
		List<PostDto> list = postService.selectAll(page,type);
		return new ResponseEntity<List<PostDto>>(list,HttpStatus.OK);
	}
	@GetMapping("/post/{id}")
	public ResponseEntity<?>  selectById(@PathVariable int id, Model model, HttpSession session) throws SQLException{
		// 추후 댓글이랑 합친거로 만들기
		//UserDto user = (UserDto) session.getAttribute("user");
		PostCommentDto postCommentDto = postService.selectPostAndComment(id);
		return new ResponseEntity<PostCommentDto>(postCommentDto,HttpStatus.OK);
	}

	@PostMapping("/post")
	public ResponseEntity<?> insert(@RequestBody PostDto postDto) throws Exception{
		postService.insert(postDto);
		return new ResponseEntity<>(null,HttpStatus.OK);
	}
	
	@PutMapping("/post/{postNum}")
	public ResponseEntity<?> update(@RequestBody PostDto postDto) throws Exception{
		postService.update(postDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@DeleteMapping("/post/{postNum}")
	public ResponseEntity<?> delete(@PathVariable int postNum) throws Exception{
		postService.delete(postNum);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/post/searchByAuthor/{type}/{author}")
	public ResponseEntity<?> searchByAuthor(@PathVariable String type,@PathVariable String author) throws SQLException{
		List<PostDto> list = postService.searchByAuthor(type,author);
		return new ResponseEntity<List<PostDto>>(list,HttpStatus.OK);
	}

	@GetMapping("/post/searchByTitle/{type}/{title}")
	public ResponseEntity<?> searchByTitle(@PathVariable String type,@PathVariable String title) throws SQLException{
		List<PostDto> list = postService.searchByTitle(type,title);
		return new ResponseEntity<List<PostDto>>(list,HttpStatus.OK);
	}


	@GetMapping("/post/myinfo/{userId}")
	public ResponseEntity<?> myWriteList(@PathVariable String userId) throws  SQLException{
		List<MyInfoWriteDto>list = postService.myWriteList(userId);
		return new ResponseEntity<List<MyInfoWriteDto>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/post/boardcnt/{type}")
	public ResponseEntity<Integer> boardcnt(@PathVariable String type) throws SQLException{
		return new ResponseEntity<Integer>(postService.getBoardCnt(type),HttpStatus.OK);
	}
	// 베스트 게시글
	@GetMapping("/best")
	public ResponseEntity<?> bestPost(@RequestParam("page") int page) throws SQLException {
		List<PostDto> list = postService.bestPost(page);
		return new ResponseEntity<List<PostDto>>(list,HttpStatus.OK);
	}
	// 베스트 게시글
	@GetMapping("/bestcnt")
	public ResponseEntity<?> getBestBoardCnt() throws SQLException {
		return new ResponseEntity<Integer>(postService.getBestBoardCnt(),HttpStatus.OK);
	}
}
