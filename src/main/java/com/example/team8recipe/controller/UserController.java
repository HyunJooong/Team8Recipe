package com.example.team8recipe.controller;

import com.example.team8recipe.dto.SignupRequestDto;
import com.example.team8recipe.jwt.JwtUtil;
import com.example.team8recipe.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
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
    @PostMapping("/signup/mailConfirm")
    @ResponseBody
    public String mailConfirm(@RequestParam String email) throws Exception {
        String code = emailService.sendSimpleMessage(email);
        log.info("인증코드 : " + code);
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
