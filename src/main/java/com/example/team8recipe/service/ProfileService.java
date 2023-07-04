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


    public ProfileResponseDto getUsers(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원정보가 존재하지 않습니다."));
        ProfileResponseDto profileResponseDto = new ProfileResponseDto(user);
        return profileResponseDto;
    }

    public ProfileResponseDto updateProfile(Long userId,ProfileRequestDto updateProfile) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원정보가 존재하지 않습니다."));

        user.setUserName(updateProfile.getUserName());
        user.setIntro(updateProfile.getIntro());
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user = userRepository.save(user);

        ProfileResponseDto profileResponseDto = new ProfileResponseDto(user);
        return profileResponseDto;
    }
}
