package com.ssafy.mvc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mvc.dto.BookDto;
import com.ssafy.mvc.model.repo.BookRepo;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepo bkrepo;
	
	@Override
	public List<BookDto> getBooklist() throws Exception {
		return bkrepo.getBooklist();
	}

}
