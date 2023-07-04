package com.example.team8recipe.serrvice;

import com.example.team8recipe.dto.SignupRequestDto;
import com.example.team8recipe.entity.User;
import com.example.team8recipe.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //사용자 회원가입
    public void userSignup(SignupRequestDto requestDto) {
        String userId = requestDto.getUserId();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String username = requestDto.getUsername();
        String intro = requestDto.getIntro();

        System.out.println("userId = " + userId);
        //유저 아이디 확인
        Optional<User> checkUsername = userRepository.findByUserId(userId);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(userId, password, username, intro);
        userRepository.save(user);

    }

}
