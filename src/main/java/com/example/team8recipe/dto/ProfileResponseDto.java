package com.example.team8recipe.dto;

import com.example.team8recipe.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponseDto {
    private String user_id;
    private String userName;
    private String password;
    private String intro;

    public ProfileResponseDto(User user) {
        this.user_id = user.getUserId();
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.intro = user.getIntro();
    }
}
