package com.example.team8recipe.controller;

import com.example.team8recipe.dto.SignupRequestDto;
import com.example.team8recipe.jwt.JwtUtil;
import com.example.team8recipe.service.EmailService;
import com.example.team8recipe.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final EmailService emailService;
    private final JwtUtil jwtUtil;

    @GetMapping("/signup-page")
    public String userSignup(){
        return "signup";
    }

    @GetMapping("/posts-page")
    public String postsSignup(){
        return "posts";
    }

    @PostMapping("/signup")
    public String userSignup(@Valid @ModelAttribute SignupRequestDto requestDto){
        userService.userSignup(requestDto);
        return "redirect:/user/login-page";
    }

    // 이메일 인증
    @PostMapping("/signup/mailConfirm")
    @ResponseBody
    public void mailConfirm(@RequestParam String email, HttpServletResponse response) throws Exception {
           emailService.sendSimpleMessage(email);
    }


    @PostMapping("/signup/checkCode")
    @ResponseBody
    public String checkCode(@RequestParam String code) {
        if (code.equals(emailService.getEPw())) {
            return "success";
        } else {
            return "failure";
        }
    }

   @GetMapping("/login-page")
    public String userlogin(){
        return "login";
   }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response,String name){
       jwtUtil.deleteCookie(request,response,"Authorization");
       return "redirect:/";
    }

    @GetMapping("/profile-page")
    public String getProfilePage() {
        return "profile";
    }

    @GetMapping("/profile-page/pwchange")
    public String getPwChangePage() {
        return "pwchange";
    }

}
