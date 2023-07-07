package com.example.team8recipe.dto;

import com.example.team8recipe.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class CommentListViewResponseDto {

    private String body; // 본문
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;

    public CommentListViewResponseDto(Comment comment) {
        super();

        this.id = comment.getId();
        this.body = comment.getBody();
        this.userName = comment.getUser().getUsername();
        this.createdAt =comment.getPost().getCreatedAt();
        this.modifiedAt = comment.getPost().getModifiedAt();
    }
}