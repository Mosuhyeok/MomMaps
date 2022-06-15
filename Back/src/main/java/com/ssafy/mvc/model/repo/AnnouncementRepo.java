package com.ssafy.mvc.model.repo;

import com.ssafy.mvc.dto.AnnouncementDto;
import com.ssafy.mvc.dto.PostDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import com.ssafy.mvc.dto.AnnouncementDto;
@Mapper
public interface AnnouncementRepo {

    // 전체 조회
    List<AnnouncementDto> selectAll() throws SQLException;
    // 디테일
    AnnouncementDto selectById(int id) throws  SQLException;
    // 쓰기
    void insert(AnnouncementDto announcementDto) throws SQLException;
    // 수정
    void update(AnnouncementDto announcementDto) throws SQLException;
    // 삭제
    void delete(int id) throws SQLException;
    // 검색
    List<AnnouncementDto> searchByTitle(String title) throws SQLException;
    List<AnnouncementDto> searchByAuthor(String author) throws SQLException;
}
