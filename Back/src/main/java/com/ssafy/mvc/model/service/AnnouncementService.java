package com.ssafy.mvc.model.service;

import com.ssafy.mvc.dto.AnnouncementDto;
import com.ssafy.mvc.dto.CommentDto;
import com.ssafy.mvc.dto.PostCommentDto;
import com.ssafy.mvc.dto.PostDto;
import com.ssafy.mvc.model.repo.AnnouncementRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnnouncementService {
    private final AnnouncementRepo announcementRepo;

    public List<AnnouncementDto> selectAll() throws SQLException {
        return announcementRepo.selectAll();
    }


    public AnnouncementDto selectById(int id) throws SQLException {
        AnnouncementDto announcementDto = announcementRepo.selectById(id);
        return announcementDto;
    }

    @Transactional
    public void insert(AnnouncementDto announcementDto) throws Exception {
        announcementRepo.insert(announcementDto);
    }

    @Transactional
    public void update(AnnouncementDto announcementDto) throws Exception {
        announcementRepo.update(announcementDto);
    }
    @Transactional
    public void delete(int postNum) throws Exception {
        announcementRepo.delete(postNum);
    }
    // 이름으로 검색
    public List<AnnouncementDto> searchByAuthor(String author) throws SQLException{
        return announcementRepo.searchByAuthor(author);
    }

    public List<AnnouncementDto> searchByTitle(String title) throws SQLException{
        return announcementRepo.searchByTitle(title);
    }
}
