package com.ssafy.mvc.model.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssafy.mvc.dto.CommentDto;
import com.ssafy.mvc.model.repo.CommentRepository;
import com.ssafy.mvc.validation.Validation;

@Service
@RequiredArgsConstructor
public class CommentService {

	private final CommentRepository repo;
	private final Validation validation;
	

	@Transactional
	public int insert(CommentDto comment) throws Exception {
		return repo.insert(comment);
	}


	@Transactional
	public int delete(int id) throws Exception {
		CommentDto origin = repo.searchById(id);
		if (origin == null) throw new Exception();
		return repo.delete(id);
	}


	@Transactional
	public int update(CommentDto comment) throws Exception {
		CommentDto origin = repo.searchById(comment.getId());
		if (origin == null) throw new Exception();
		return repo.update(comment);
	}

}
