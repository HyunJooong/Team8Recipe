package com.example.team8recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoDto {
    String userName;
//    boolean isAdmin;
    private ProfileResponseDto profileResponseDto;
}