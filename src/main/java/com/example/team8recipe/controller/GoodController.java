package com.example.team8recipe.controller;


import com.example.team8recipe.repository.GoodRepository;
import com.example.team8recipe.security.UserDetailsImpl;
import com.example.team8recipe.service.GoodService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Good")
public class GoodController {
    private final GoodService goodService;

    public GoodController( GoodService goodService) {
        this.goodService = goodService;
    }

    @DeleteMapping("/posts/{id}/click")
    public void goodClick(@PathVariable Long id,@RequestParam boolean requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println("id = " + id);
        System.out.println("requestDto = " + requestDto);

        goodService.goodClick(id,requestDto,userDetails);
    }

    @GetMapping("/getGood/{id}")
    public boolean getGood(@PathVariable Long id,  @AuthenticationPrincipal UserDetailsImpl userDetails){
        return goodService.getGood(id, userDetails);
    }

    @GetMapping("/goodnumber/{id}")
    public int number(@PathVariable Long id){
        return goodService.number(id);
    }
}