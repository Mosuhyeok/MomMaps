package com.ssafy.mvc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mvc.dto.ChartDto;
import com.ssafy.mvc.dto.DealInfoDto;
import com.ssafy.mvc.dto.HouseAllDto;
import com.ssafy.mvc.dto.HouseInfo;
import com.ssafy.mvc.dto.SGDcodeDto;
import com.ssafy.mvc.model.repo.HouseRepo;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseRepo Hsrepo;

	@Override
	public List<SGDcodeDto> getSido() throws Exception {
		return Hsrepo.getSido();
	}

	@Override
	public List<SGDcodeDto> getGugun(String sido) throws Exception {
		return Hsrepo.getGugun(sido);
	}

	@Override
	public List<SGDcodeDto> getDong(String gugun) throws Exception {
		return Hsrepo.getDong(gugun);
	}

	@Override
	public List<HouseInfo> getHouse(String dong, int page) throws Exception {
		return Hsrepo.getHouse(dong,page);
	}

	@Override
	public List<DealInfoDto> HouseDetail(String aptName,String dong) throws Exception {
		return Hsrepo.HouseDetail(aptName,dong);
	}

	@Override
	public int getHousecnt(String dong) throws Exception {
		return Hsrepo.getHousecnt(dong);
	}

	@Override
	public List<ChartDto> getAVG(String dong) throws Exception {
		return Hsrepo.getAVG(dong);
	}

	@Override
	public List<HouseAllDto> getHouseAll() throws Exception {
		return Hsrepo.getHouseAll();
	}
	

}
