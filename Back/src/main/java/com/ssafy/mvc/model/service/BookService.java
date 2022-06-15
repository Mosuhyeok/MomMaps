package com.ssafy.mvc.model.service;

import java.util.List;

import com.ssafy.mvc.dto.BookDto;

public interface BookService {
	List<BookDto> getBooklist() throws Exception;
}
