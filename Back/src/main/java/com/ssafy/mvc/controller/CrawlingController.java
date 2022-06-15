package com.ssafy.mvc.controller;

import com.ssafy.mvc.model.service.CrawlingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CrawlingController {

    private final CrawlingService crawlingService;
    @GetMapping("/crawling")
    public List<String> info() throws IOException {
        return  crawlingService.info();
    }
}
