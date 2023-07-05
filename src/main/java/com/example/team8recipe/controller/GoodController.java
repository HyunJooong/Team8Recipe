package com.example.team8recipe.controller;

import com.example.team8recipe.dto.GoodRequestDto;
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
    public void goodClick(@PathVariable Long id,@RequestBody GoodRequestDto requestDto,@AuthenticationPrincipal UserDetailsImpl userDetails){
        goodService.goodClick(id,requestDto,userDetails);
    }
}
