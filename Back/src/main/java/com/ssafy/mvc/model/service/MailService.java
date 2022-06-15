package com.ssafy.mvc.model.service;


import com.ssafy.mvc.dto.MailDto;
import com.ssafy.mvc.model.repo.MailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final MailRepository mailRepository;

    public ResponseEntity<?> mailSend(@RequestBody String email) throws Exception{
        Random random = new Random();
        int checkNum = random.nextInt(888888) + 111111;

        // 기존 인증 한번 해서 테이블에 값이 있는 경우 삭제하는 작업
        mailRepository.delete(email);

        // 메일 보내기
        MimeMessage m = mailSender.createMimeMessage();
        MimeMessageHelper h = new MimeMessageHelper(m,"UTF-8");
        h.setFrom("projectssafydaejeon@naver.com");
        System.out.println(email);
        h.setTo(email);
        h.setSubject("인증 관련 메일입니다");
        h.setText("인증번호 입니다" + checkNum);
        mailSender.send(m);

        mailRepository.insert(new MailDto(email,checkNum));

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // 이메일 검증
    public ResponseEntity<?> verify(@RequestBody MailDto mailDto) throws Exception{

        MailDto origin = mailRepository.select(mailDto.getEmail());

        if(origin.getValue() == mailDto.getValue()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        throw new IllegalArgumentException("인증번호가 일치하지 않습니다");
    }
}
