package com.example.team8recipe.controller;

import com.example.team8recipe.dto.SignupRequestDto;
import com.example.team8recipe.entity.User;
import com.example.team8recipe.jwt.JwtUtil;
import com.example.team8recipe.serrvice.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public User userSignup(@Valid @RequestBody SignupRequestDto requestDto){
       return userService.userSignup(requestDto);
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, String name) {

        jwtUtil.deleteCookie(request, response, "Authorization");
    }



}
