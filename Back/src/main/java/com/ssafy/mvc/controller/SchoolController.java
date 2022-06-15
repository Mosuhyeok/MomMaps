package com.ssafy.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.mvc.dto.SchoolDto;
import com.ssafy.mvc.model.service.SchoolService;

@RestController
@RequestMapping("/school")
public class SchoolController {
	@Autowired
	private SchoolService scService;
	
	@GetMapping("/list")
	public ResponseEntity<List<SchoolDto>> schoollist() throws Exception {
		return new ResponseEntity<List<SchoolDto>>(scService.getSchoollist(),HttpStatus.OK);
	}
}
