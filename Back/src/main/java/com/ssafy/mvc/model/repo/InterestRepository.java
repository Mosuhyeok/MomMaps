package com.ssafy.mvc.model.repo;

import com.ssafy.mvc.dto.InterestDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface InterestRepository {

    // 관심지역 추가
    void insert(InterestDto interestDto) throws SQLException;

    // 관심지역 조회
    List<InterestDto> selectById(String userId) throws SQLException;

    // 관심지역 삭제
    void remove(InterestDto interestDto) throws SQLException;
}
