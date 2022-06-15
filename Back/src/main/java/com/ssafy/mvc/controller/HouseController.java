package com.ssafy.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mvc.dto.ChartDto;
import com.ssafy.mvc.dto.DealInfoDto;
import com.ssafy.mvc.dto.HouseAllDto;
import com.ssafy.mvc.dto.HouseInfo;
import com.ssafy.mvc.dto.SGDcodeDto;
import com.ssafy.mvc.model.service.HouseService;

@RestController
@RequestMapping("/house")
public class HouseController {
	
	@Autowired
	private HouseService HsService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<SGDcodeDto>> sido() throws Exception {
		return new ResponseEntity<List<SGDcodeDto>>(HsService.getSido(),HttpStatus.OK);
	}
	
	@GetMapping("/gugun")
	public ResponseEntity<List<SGDcodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
		return new ResponseEntity<List<SGDcodeDto>>(HsService.getGugun(sido),HttpStatus.OK);
	}
	
	@GetMapping("/dong")
	public ResponseEntity<List<SGDcodeDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
		return new ResponseEntity<List<SGDcodeDto>>(HsService.getDong(gugun),HttpStatus.OK);
	}
	
	@GetMapping("/houseList")
	public ResponseEntity<List<HouseInfo>> houselist(@RequestParam("dong") String dong, @RequestParam("page") int page) throws Exception {
		return new ResponseEntity<List<HouseInfo>>(HsService.getHouse(dong,page),HttpStatus.OK);
	}
	
	@GetMapping("/houseDetail")
	public ResponseEntity<List<DealInfoDto>> housedetail(@RequestParam("aptName") String aptName, @RequestParam("dong") String dong) throws Exception {

		ResponseEntity<List<DealInfoDto>> list = new ResponseEntity<List<DealInfoDto>>(HsService.HouseDetail(aptName,dong),HttpStatus.OK);

		return list;
	}
	
	@GetMapping("/houseCnt")
	public ResponseEntity<Integer> housecnt(@RequestParam("dong") String dong) throws Exception {
		return new ResponseEntity<Integer>(HsService.getHousecnt(dong),HttpStatus.OK);
	}
	
	@GetMapping("/houseAVG")
	public ResponseEntity<List<ChartDto>> houseavg(@RequestParam("dong") String dong) throws Exception {
		System.out.println(dong);
		ResponseEntity<List<ChartDto>> list = new ResponseEntity<List<ChartDto>>(HsService.getAVG(dong),HttpStatus.OK);
		System.out.println(list);
		return list;
	}
	
	@GetMapping("/houseAllList")
	public ResponseEntity<List<HouseAllDto>> housealllist() throws Exception {
		//ResponseEntity<List<HouseAllDto>> list = new ResponseEntity<List<HouseAllDto>>(HsService.getHouseAll(),HttpStatus.OK);
		return new ResponseEntity<List<HouseAllDto>>(HsService.getHouseAll(),HttpStatus.OK);
	}
}
