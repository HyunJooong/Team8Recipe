package com.example.team8recipe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {

    @Pattern(regexp = "^[a-z0-9]{4,10}$",
            message = "최소 4자 이상, 10자 이하이며 알파벳 소문자, 숫자로 구성")
    private String userId;

    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+{}:\"<>?,.\\\\/]{7,15}$",
                    message = "최소 7  자 이상, 15자 이하이며 알파벳 대소문자, 숫자로 구성")
    private String password;

    @NotBlank
    private String username;

    @Size(min = 10, max = 1000) // 자기소개 최대 10자 이상
    private String intro;


}
