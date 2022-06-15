package com.ssafy.mvc.model.repo;

import java.util.List;

import com.ssafy.mvc.dto.ChartDto;
import com.ssafy.mvc.dto.DealInfoDto;
import com.ssafy.mvc.dto.HouseAllDto;
import com.ssafy.mvc.dto.HouseInfo;
import com.ssafy.mvc.dto.SGDcodeDto;

public interface HouseRepo {
	List<SGDcodeDto> getSido() throws Exception;
	List<SGDcodeDto> getGugun(String sido) throws Exception;
	List<SGDcodeDto> getDong(String gugun) throws Exception;
	List<HouseInfo> getHouse(String dong, int page) throws Exception;
	List<DealInfoDto> HouseDetail(String aptName,String dong) throws Exception;
	int getHousecnt(String dong) throws Exception;
	List<ChartDto> getAVG(String dong) throws Exception;
	List<HouseAllDto> getHouseAll() throws Exception;
}
