package com.example.team8recipe.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequestDto {
    private String user_id;
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+{}:\"<>?,.\\\\/]{7,15}$",
            message = "최소 7  자 이상, 15자 이하이며 알파벳 대소문자, 숫자로 구성")
    private String password;
    private String userName;
    @Size(min = 10, max = 1000) // 자기소개 최대 10자 이상
    private String intro;
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+{}:\"<>?,.\\\\/]{7,15}$",
            message = "최소 7  자 이상, 15자 이하이며 알파벳 대소문자, 숫자로 구성")
    private String currentPassword;
    private String newPassword;
}