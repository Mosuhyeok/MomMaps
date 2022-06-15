package com.ssafy.mvc.controller;

import com.ssafy.mvc.dto.MailDto;
import com.ssafy.mvc.model.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class MailController {

    private final MailService mailService;

    @PostMapping("/mail/{email:.+}")
    public ResponseEntity<?> mailSend(@PathVariable String email) throws Exception{
        return mailService.mailSend(email);
    }

    @PostMapping("/mail/auth")
    public ResponseEntity<?> verify(@RequestBody MailDto mailDto) throws Exception{
        return mailService.verify(mailDto);
    }
}
