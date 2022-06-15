package com.ssafy.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mvc.dto.DealInfoDto;
import com.ssafy.mvc.model.service.DealInfoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/deal")
@Slf4j
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class RestDealController {
	
	@Autowired
	private DealInfoService dealInfoService;
	
	
	@PostMapping("/getAptList")
	public List<DealInfoDto> getAptList(@RequestParam("dong") String dong){
		log.debug("getAptList 호출");
		List<DealInfoDto> list = dealInfoService.getByDongName(dong);
		log.debug(""+list);
		return list;		// list형태 Json으로 넘기기
		
	}
	
	
	
}
