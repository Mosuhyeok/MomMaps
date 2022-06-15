package com.ssafy.mvc.model.repo;

import java.util.List;

import com.ssafy.mvc.dto.SchoolDto;

public interface SchoolRepo {
	List<SchoolDto> getSchoollist() throws Exception;
}
