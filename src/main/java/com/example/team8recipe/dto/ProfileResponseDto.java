package com.example.team8recipe.dto;

import com.example.team8recipe.entity.User;
import lombok.Getter;

@Getter
public class ProfileResponseDto {
    private long id;
    private String password;
    private String userName;
    private String intro;

    public ProfileResponseDto(User user) {
        this.id = user.getId();
        this.password = user.getPassword();
        this.userName = user.getUserName();
        this.intro = user.getIntro();
    }
}
