package com.example.team8recipe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequestDto {
    private String user_id;
    private String password;
    private String userName;
    private String intro;
    private String currentPassword;
    private String newPassword;
}
