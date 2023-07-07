package com.example.team8recipe.dto;

import com.example.team8recipe.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentViewResponseDto extends ApiResponseDto{

    private String body; // 본문
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long post_id;

    public CommentViewResponseDto(Comment comment) {
        super();

        this.post_id = comment.getId();
        this.body = comment.getBody();
        this.userName = comment.getUser().getUsername();
        this.createdAt =comment.getPost().getCreatedAt();
        this.modifiedAt = comment.getPost().getModifiedAt();
    }
}