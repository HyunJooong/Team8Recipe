package com.example.team8recipe.dto;

import com.example.team8recipe.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentViewResponseDto extends ApiResponseDto{

    private String body; // 본문
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentViewResponseDto(Comment comment) {
        super();
        this.body = comment.getBody();
        this.userName = comment.getUser().getUsername();
        this.createdAt =comment.getPost().getCreatedAt();
        this.modifiedAt = comment.getPost().getModifiedAt();
    }
}
