package com.example.team8recipe.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.transaction.annotation.Transactional;

@Getter
@Setter
@Transactional
public class ProfileRequestDto {
    private long id;
    private String password;
    private String userName;
    private String intro;
    private String confirmPassword;
}
