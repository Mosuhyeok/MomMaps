package com.ssafy.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mvc.dto.BookDto;
import com.ssafy.mvc.model.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bkService;
	
	@GetMapping("/list")
	public ResponseEntity<List<BookDto>> booklist() throws Exception {
		return new ResponseEntity<List<BookDto>>(bkService.getBooklist(),HttpStatus.OK);
	}
}
