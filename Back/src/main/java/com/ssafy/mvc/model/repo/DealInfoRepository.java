package com.ssafy.mvc.model.repo;

import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mvc.dto.DealInfoDto;

@Mapper
public interface DealInfoRepository {
	List<DealInfoDto> selectAllByDongName(String dongName);

	List<DealInfoDto> selectAllByAptCode(int aptCode);
}
