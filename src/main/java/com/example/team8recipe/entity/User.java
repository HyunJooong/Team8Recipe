package com.example.team8recipe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId; // 사용자 아이디

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false)
    private String username; //사용자 이름

    @Column(nullable = false)
    private String intro; // 사용자 자기소개

    @OneToMany(mappedBy = "user" ,cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();



    public User(String userId, String password, String username, String intro) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.intro = intro;
    }
}
