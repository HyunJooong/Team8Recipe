package com.example.team8recipe.controller;

import com.example.team8recipe.dto.ProfileRequestDto;
import com.example.team8recipe.dto.ProfileResponseDto;
import com.example.team8recipe.dto.UserInfoDto;
import com.example.team8recipe.security.UserDetailsImpl;
import com.example.team8recipe.service.ProfileService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/profile")
    public UserInfoDto getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response) throws IOException {
        String username = userDetails.getUser().getUsername();

        String userId = userDetails.getUser().getUserId();
        ProfileResponseDto profileResponseDto = profileService.getUsers(userId);

        return new UserInfoDto(username, profileResponseDto);
    }

    @PutMapping("/profile/update/{userId}")
    public ResponseEntity<ProfileResponseDto> updateProfile(
            @Valid
            @PathVariable String userId,
            @RequestBody ProfileRequestDto updateProfile,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        String loggedInUserId = userDetails.getUser().getUserId();
        if (!loggedInUserId.equals(userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        ProfileResponseDto updatedProfile = profileService.updateProfile(userId, updateProfile);
        return ResponseEntity.ok(updatedProfile);
    }

    @PutMapping("/profile/password/{userId}")
    public ResponseEntity<ProfileResponseDto> changePassword(
            @Valid
            @PathVariable String userId,
            @RequestBody ProfileRequestDto updateProfile,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        String loggedInUserId = userDetails.getUser().getUserId();
        if (!loggedInUserId.equals(userId)) {
            throw new IllegalArgumentException("유저 권한이 존재하지 않습니다.");
        }

        ProfileResponseDto responseDto = profileService.changePassword(userId, updateProfile);
        return ResponseEntity.ok(responseDto);
    }
}
