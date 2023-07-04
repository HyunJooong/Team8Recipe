package com.example.team8recipe.controller;

import com.example.team8recipe.dto.ProfileRequestDto;
import com.example.team8recipe.dto.ProfileResponseDto;
import com.example.team8recipe.dto.UserInfoDto;
import com.example.team8recipe.entity.User;
import com.example.team8recipe.entity.UserRoleEnum;
import com.example.team8recipe.security.UserDetailsImpl;
import com.example.team8recipe.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/profile")
    public UserInfoDto getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUserName();
        UserRoleEnum role = userDetails.getUser().getRole();
        boolean isAdmin = (role == UserRoleEnum.ADMIN);

        String userId = userDetails.getUser().getUserId();
        ProfileResponseDto profileResponseDto = profileService.getUsers(userId);

        return new UserInfoDto(username, isAdmin, profileResponseDto);
    }

    @PutMapping("/profile/{userId}")
    public ProfileResponseDto updateProfile(
            @PathVariable String userId,
            @RequestBody ProfileRequestDto updateProfile,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        ProfileResponseDto profileResponseDto = profileService.updateProfile(userId, updateProfile);
        return profileResponseDto;
    }
}
