package com.ssafy.mvc.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mvc.dto.DealInfoDto;
import com.ssafy.mvc.model.repo.DealInfoRepository;

@Service
public class DealInfoServiceImpl implements DealInfoService {
	private DealInfoRepository repo;

	@Autowired
	public void setRepo(DealInfoRepository repo) {
		this.repo = repo;
	}

	@Override
	public List<DealInfoDto> getByDongName(String dongName) {
		return repo.selectAllByDongName(dongName);
	}

	@Override
	public List<DealInfoDto> getByAptCode(int aptCode) {
		return repo.selectAllByAptCode(aptCode);
	}

}
