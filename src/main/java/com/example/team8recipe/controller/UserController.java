package com.example.team8recipe.controller;

import com.example.team8recipe.dto.SignupRequestDto;
import com.example.team8recipe.jwt.JwtUtil;
import com.example.team8recipe.service.EmailService;
import com.example.team8recipe.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLEncoder;

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

    @PostMapping("/signup")
    public String userSignup(@Valid @ModelAttribute SignupRequestDto requestDto){
        userService.userSignup(requestDto);
        return "redirect:/user/login-page";
    }

    // 이메일 인증
    @PostMapping("/signup/mailConfirm")
    @ResponseBody
    public void mailConfirm(@RequestParam String email, HttpServletResponse response) throws Exception {


        if(email.matches(".*@.*")){
            String code = emailService.sendSimpleMessage(email);
            log.info("인증코드 : " + code);

            response.setStatus(200);
        }else {
            System.out.println("emailService.getEPw() = " + emailService.getEPw());
            String code = email;
            log.info("사용자 입력코드 : " + code);

            if(!emailService.getEPw().equals(code)){
                response.setStatus(401);
            }else {
                response.setStatus(200);
            }

        }
    }

    @PostMapping("/signup/success")
    public void success(@RequestParam String email, HttpServletResponse response){
        String code = email;
        log.info("사용자 입력코드 : " + code);

//        response.setStatus(401);
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

}
