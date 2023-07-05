package com.example.team8recipe.controller;

import com.example.team8recipe.dto.SignupRequestDto;
import com.example.team8recipe.jwt.JwtUtil;
import com.example.team8recipe.serrvice.UserService;
import com.example.team8recipe.service.RegisterMail;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RegisterMail registerMail;

    private final JwtUtil jwtUtil;
    public UserController(UserService userService, RegisterMail registerMail,JwtUtil jwtUtil) {
        this.userService = userService;
        this.registerMail = registerMail;
        this.jwtUtil = jwtUtil;
    }
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
    @PostMapping("login/mailConfirm")
    @ResponseBody
    String mailConfirm(@RequestParam("email") String email) throws Exception {

        String code = registerMail.sendSimpleMessage(email);
        System.out.println("인증코드 : " + code);
        return code;
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
