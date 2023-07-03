package com.example.team8recipe.dto;

import lombok.Getter;

@Getter
public class ProfileRequestDto {
    private long id;
    private String password;
    private String userName;
    private String intro;
}
