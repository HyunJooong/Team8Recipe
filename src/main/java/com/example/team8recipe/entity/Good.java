package com.example.team8recipe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "good")
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //좋아요 누른 사람   좋아요 n:1 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users")
    private User user;

    //좋아요가 눌린 게시글  좋아요 n:1 게시글
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts")
    private Post post;

    public Good(Post post, User user){
        this.post = post;
        this.user = user;
    }

}
