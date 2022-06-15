package com.ssafy.mvc.controller;

import javax.servlet.http.HttpSession;

import com.ssafy.mvc.model.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ssafy.mvc.dto.CommentDto;
import com.ssafy.mvc.dto.UserDto;

@Controller
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentRestController {
	@Autowired
	private CommentService commentService;

	@PostMapping("/")
	public ResponseEntity<?> insert(@RequestBody CommentDto comment) throws Exception {
		System.out.println(comment);
		int result = commentService.insert(comment);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/{commentNum}")
	public ResponseEntity<?> update(@RequestBody CommentDto comment, HttpSession httpSession, @PathVariable int commentNum) throws Exception {
		UserDto user = (UserDto) httpSession.getAttribute("user");
		int result = commentService.update(comment);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{commentNum}")
	public ResponseEntity<?> delete(@PathVariable int commentNum) throws Exception {
		int result = commentService.delete(commentNum);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
