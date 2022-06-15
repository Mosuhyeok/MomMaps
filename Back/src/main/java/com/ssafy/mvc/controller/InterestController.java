package com.ssafy.mvc.controller;

import com.ssafy.mvc.dto.InterestDto;
import com.ssafy.mvc.model.service.InterestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class InterestController {

    private final InterestService interestService;

    @PostMapping("/interest")
    public ResponseEntity<?> insert(@RequestBody InterestDto interestDto) throws SQLException {
        interestService.insert(interestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/interest/{userId}")
    public ResponseEntity<?> selectById (@PathVariable String userId) throws SQLException{
        List<InterestDto> list = interestService.selectById(userId);
        return new ResponseEntity< List<InterestDto> >(list,HttpStatus.OK);
    }

    @DeleteMapping("/interest")
    public ResponseEntity<?> remove(@RequestBody InterestDto interestDto) throws SQLException{
        interestService.remove(interestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
