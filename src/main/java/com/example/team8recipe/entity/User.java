package com.example.team8recipe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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
    private String userName; //사용자 이름

    @Column(nullable = false)
    private String intro; // 사용자 자기소개

    public User(Long id, String userId, String password, String userName, String intro) {
        this.id = id;
        this.userId = userId;
        this.password = password;
        this.userName = userName;
        this.intro = intro;
    }
}
