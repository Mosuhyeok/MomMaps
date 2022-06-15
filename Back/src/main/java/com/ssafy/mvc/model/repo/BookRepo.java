package com.ssafy.mvc.model.repo;

import java.util.List;
import com.ssafy.mvc.dto.BookDto;

public interface BookRepo {
	List<BookDto> getBooklist() throws Exception;
}
