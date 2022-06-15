package com.ssafy.mvc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mvc.dto.SchoolDto;
import com.ssafy.mvc.model.repo.SchoolRepo;

@Service
public class SchoolServiceImpl implements SchoolService {
	
	@Autowired
	private SchoolRepo screpo;
	@Override
	public List<SchoolDto> getSchoollist() throws Exception {
		return screpo.getSchoollist();
	}

}
