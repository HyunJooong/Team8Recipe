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

    @PostMapping("/signup")
    public String userSignup(@Valid @ModelAttribute SignupRequestDto requestDto){
        userService.userSignup(requestDto);
        return "redirect:/user/login-page";
    }

    // 이메일 인증
    @PostMapping("/signup/mailConfirm")
    @ResponseBody
    public String mailConfirm(@RequestParam String email) throws Exception {
        String code = emailService.sendSimpleMessage(email);
        log.info("인증코드 : " + code);
        return code;
    }

    @PostMapping("/signup/checkCode")
    @ResponseBody
    public String checkCode(@RequestParam String code) {
        // 실제로는 서버에서 저장한 인증번호와 사용자가 입력한 인증번호를 비교하여 처리해야합니다.
        // 여기서는 단순히 입력한 인증번호와 동일한지만 확인하는 예시입니다.
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

}
