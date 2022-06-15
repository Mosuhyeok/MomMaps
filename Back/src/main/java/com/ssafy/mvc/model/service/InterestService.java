package com.ssafy.mvc.model.service;

import com.ssafy.mvc.dto.InterestDto;
import com.ssafy.mvc.model.repo.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InterestService {

    private final InterestRepository interestRepository;

    public void insert(InterestDto interestDto) throws SQLException{
        interestRepository.insert(interestDto);
    }
    public List<InterestDto> selectById(String userId) throws SQLException{
        return interestRepository.selectById(userId);
    }
    public void remove(@RequestBody InterestDto interestDto) throws SQLException{
        interestRepository.remove(interestDto);
    }
}
