package com.example.team8recipe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment {

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

    public Comment(String body) {
        this.body = body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPost(Post post) {
        this.post = post;
    }


}
