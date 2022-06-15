package com.ssafy.mvc.model.service;

import java.util.List;

import com.ssafy.mvc.dto.DealInfoDto;

public interface DealInfoService {
	List<DealInfoDto> getByDongName(String dongName);
	List<DealInfoDto> getByAptCode(int aptCode);
}
