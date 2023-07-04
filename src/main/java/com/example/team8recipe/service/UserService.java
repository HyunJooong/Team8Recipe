package com.example.team8recipe.serrvice;

import com.example.team8recipe.dto.LoginRequestDto;
import com.example.team8recipe.dto.SignupRequestDto;
import com.example.team8recipe.entity.User;
import com.example.team8recipe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    //사용자 회원가입
    public void userSignup(SignupRequestDto requestDto) {
        String userId = requestDto.getUserId();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String username = requestDto.getUsername();
        String intro = requestDto.getIntro();

        //유저 아이디 확인
        Optional<User> checkUsername = userRepository.findByUserId(userId);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(userId, password, username, intro);
        userRepository.save(user);

        return user;
    }

}
