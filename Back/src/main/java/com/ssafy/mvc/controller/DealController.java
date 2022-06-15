package com.ssafy.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/deal")
@Controller
public class DealController {

	@GetMapping("/search")
	public String search() {
		return "apartment_dong";
	}
}
