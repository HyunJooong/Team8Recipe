package com.example.team8recipe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {

    private Long post_id; // 게시글 id
    private String body; //게시글 본문
}
