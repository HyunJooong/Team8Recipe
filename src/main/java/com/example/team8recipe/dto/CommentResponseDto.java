package com.example.team8recipe.dto;

import com.example.team8recipe.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto extends ApiResponseDto{

    private String body; // 본문
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        super();
        this.body = comment.getBody();
        this.userName = comment.getUser().getUsername();
        this.createdAt =comment.getPost().getCreatedAt();
        this.modifiedAt = comment.getPost().getModifiedAt();
    }
}
