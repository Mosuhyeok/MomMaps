package com.ssafy.mvc.model.service;

import java.util.List;
import com.ssafy.mvc.dto.SchoolDto;

public interface SchoolService {
	List<SchoolDto> getSchoollist() throws Exception;
}
