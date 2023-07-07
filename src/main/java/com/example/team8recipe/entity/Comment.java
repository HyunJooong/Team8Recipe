package com.example.team8recipe.entity;

import com.example.team8recipe.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String body; // 댓글 본문

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post; //게시글과 연관 관계

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 글쓴이와 연관 관계

    public Comment(CommentRequestDto commentRequestDto,User user, Post post)
    {
        this.body = commentRequestDto.getBody();
        this.user = user;
        this.post = post;
    }

//    public void setBody(String body) {
//        this.body = body;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public void setPost(Post post) {
//        this.post = post;
//    }


}