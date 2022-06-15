package com.ssafy.mvc.model.repo;

import com.ssafy.mvc.dto.MailDto;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface MailRepository {

    MailDto select(String userId) throws SQLException;
    int insert(MailDto mailDto) throws SQLException;
    void delete(String userId) throws SQLException;
}
