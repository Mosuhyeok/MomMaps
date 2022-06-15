package com.ssafy.mvc.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ssafy.mvc.dto.PostCommentDto;
import com.ssafy.mvc.dto.PostDto;
import com.ssafy.mvc.dto.UserDto;
import com.ssafy.mvc.model.service.PostService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
public class PostController {
	
	private final PostService postService;
	
//	@GetMapping("/post/{id}")
//	public String selectById(@PathVariable int id, Model model, HttpSession session) throws SQLException{
//		// 추후 댓글이랑 합친거로 만들기
//		UserDto user = (UserDto) session.getAttribute("user");
//		PostCommentDto postCommentDto = postService.selectPostAndComment(id);
//		model.addAttribute("postCommentDto",postCommentDto);
//		model.addAttribute("user",user);
//		return "detail";
//	}

	
//	@GetMapping("/post")
//	public String post(Model model, HttpSession session) throws SQLException {
//		UserDto user = (UserDto) session.getAttribute("user");
//		List<PostDto> list = postService.selectAll();
//		model.addAttribute("list",list);
//		model.addAttribute("user",user);
//		return "post";
//	}
	
	@GetMapping("/post/save")
	public String postSave() {
		return "postSave";
	}
	
	@GetMapping("/post/update/{id}")
	public String postUpdate(Model model, @PathVariable int id) throws SQLException {
		PostDto post = postService.selectById(id);
		model.addAttribute("post",post);
		return "postUpdate";
	}
	
	
}
