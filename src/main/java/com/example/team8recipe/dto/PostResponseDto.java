package com.example.team8recipe.dto;

import com.example.team8recipe.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
public class PostResponseDto extends ApiResponseDto{
    private Long id;
    private String title;
    private String content;
    private LocalDateTime creatAt;
    private LocalDateTime modifiedDate;
    private String username;
//    private List<CommentResponseDto> comments;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatAt();
        this.modifiedAt = post.getModifiedDate();
        this.username = post.getUser().getUsername();
//        this.comments = post.getComments().stream()
//                .map(CommentResponseDto::new)
//                .sorted(Comparator.comparing(CommentResponseDto::getCreatedAt).reversed()) // 작성날짜 내림차순
//                .toList();
    }
}
