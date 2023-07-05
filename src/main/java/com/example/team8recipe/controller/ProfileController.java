package com.example.team8recipe.controller;

import com.example.team8recipe.dto.ProfileRequestDto;
import com.example.team8recipe.dto.ProfileResponseDto;
import com.example.team8recipe.dto.UserInfoDto;
import com.example.team8recipe.entity.User;
import com.example.team8recipe.entity.UserRoleEnum;
import com.example.team8recipe.security.UserDetailsImpl;
import com.example.team8recipe.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/profile")
    public UserInfoDto getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
//        UserRoleEnum role = userDetails.getUser().getRole();
//        boolean isAdmin = (role == UserRoleEnum.ADMIN);

        String userId = userDetails.getUser().getUserId();
        ProfileResponseDto profileResponseDto = profileService.getUsers(userId);

        return new UserInfoDto(username, profileResponseDto);
//        return new UserInfoDto(username, isAdmin, profileResponseDto);
    }

    @PutMapping("/profile/{userId}")
    public ResponseEntity<ProfileResponseDto> updateProfile(
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

    @PutMapping("/profile/{userId}/password")
    public ResponseEntity<String> changePassword(
            @PathVariable String userId,
            @RequestParam String newPassword,
            @RequestParam String confirmPassword,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        if (!newPassword.equals(confirmPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String loggedInUserId = userDetails.getUser().getUserId();
        if (!loggedInUserId.equals(userId)) {
            throw new IllegalArgumentException("유저권한이 일치하지 않습니다.");
        }

        profileService.changePassword(userId, newPassword);
        return ResponseEntity.ok("비밀번호가 변경되었습니다.");
    }
}
