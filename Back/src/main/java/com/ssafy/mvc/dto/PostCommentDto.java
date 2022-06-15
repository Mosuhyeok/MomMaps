package com.ssafy.mvc.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PostCommentDto {
	
	private PostDto postDto;
	private List<CommentDto> commentDtos = new ArrayList<>();
}
