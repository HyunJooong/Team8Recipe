package com.example.team8recipe.service;

import com.example.team8recipe.dto.ProfileRequestDto;
import com.example.team8recipe.dto.ProfileResponseDto;
import com.example.team8recipe.entity.User;
import com.example.team8recipe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileResponseDto getUsers(String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원정보가 존재하지 않습니다."));
        return new ProfileResponseDto(user);
    }

    public ProfileResponseDto updateProfile(String userId, ProfileRequestDto updateProfile) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원정보가 존재하지 않습니다."));

        user.setUsername(updateProfile.getUserName());
        user.setIntro(updateProfile.getIntro());

        user = userRepository.save(user);

        return new ProfileResponseDto(user);
    }

    public ProfileResponseDto changePassword(String userId, ProfileRequestDto updateProfile) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원정보가 존재하지 않습니다."));

        String currentPassword = updateProfile.getCurrentPassword();

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }

        String newPassword = passwordEncoder.encode(updateProfile.getNewPassword());;
        user.setPassword(newPassword);

        user = userRepository.save(user);

        return new ProfileResponseDto(user);
    }
}