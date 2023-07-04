package com.example.team8recipe.dto;

import com.example.team8recipe.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponseDto {
    private long id;
    private String userName;
    private String intro;

    public ProfileResponseDto(User user) {
        this.id = user.getId();
        this.userName = user.getUsername();
        this.intro = user.getIntro();
    }
}
