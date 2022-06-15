package com.ssafy.mvc.controller;

import com.ssafy.mvc.dto.AnnouncementDto;
import com.ssafy.mvc.dto.PostCommentDto;
import com.ssafy.mvc.dto.PostDto;
import com.ssafy.mvc.model.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
public class AnnouncementController {

    private final AnnouncementService announcementService;


    @GetMapping("/announcement/list")
    public ResponseEntity<?> post() throws SQLException {
        List<AnnouncementDto> list = announcementService.selectAll();
        return new ResponseEntity<List<AnnouncementDto>>(list, HttpStatus.OK);
    }
    @GetMapping("/announcement/{id}")
    public ResponseEntity<?>  selectById(@PathVariable int id) throws SQLException{
        AnnouncementDto announcementDto = announcementService.selectById(id);
        return new ResponseEntity<AnnouncementDto>(announcementDto,HttpStatus.OK);
    }

    @PostMapping("/announcement")
    public ResponseEntity<?> insert(@RequestBody AnnouncementDto announcementDto) throws Exception{
        System.out.println("annoucmenet 호출");
        announcementService.insert(announcementDto);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @PutMapping("/announcement/{postNum}")
    public ResponseEntity<?> update(@RequestBody AnnouncementDto announcementDto) throws Exception{
        announcementService.update(announcementDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/announcement/{postNum}")
    public ResponseEntity<?> delete(@PathVariable int postNum) throws Exception{
        announcementService.delete(postNum);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/announcement/searchByAuthor/{author}")
    public ResponseEntity<?> searchByAuthor(@PathVariable String author) throws SQLException{
        List<AnnouncementDto> list = announcementService.searchByAuthor(author);
        return new ResponseEntity<List<AnnouncementDto>>(list,HttpStatus.OK);
    }

    @GetMapping("/announcement/searchByTitle/{title}")
    public ResponseEntity<?> searchByTitle(@PathVariable String title) throws SQLException{
        List<AnnouncementDto> list = announcementService.searchByTitle(title);
        return new ResponseEntity<List<AnnouncementDto>>(list,HttpStatus.OK);
    }



}
