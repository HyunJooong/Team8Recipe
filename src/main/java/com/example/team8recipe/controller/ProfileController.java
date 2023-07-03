package com.example.team8recipe.controller;

import com.example.team8recipe.dto.ProfileResponseDto;
import com.example.team8recipe.security.UserDetailsImpl;
import com.example.team8recipe.service.ProfileService;
import com.example.team8recipe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/profile")
    public ProfileResponseDto getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return profileService.getUsers(userDetails.getUser());
    }
}
