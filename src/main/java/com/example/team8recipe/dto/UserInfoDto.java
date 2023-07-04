package com.example.team8recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoDto {
    String userName;
    boolean isAdmin;
    private ProfileResponseDto profileResponseDto;

    public UserInfoDto(String userName, boolean isAdmin, ProfileResponseDto profileResponseDto) {
        this.userName = userName;
        this.isAdmin = isAdmin;
        this.profileResponseDto = profileResponseDto;
    }
}