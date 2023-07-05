package com.example.team8recipe.controller;

import com.example.team8recipe.dto.CommentResponseDto;
import com.example.team8recipe.dto.CommentViewResponseDto;
import com.example.team8recipe.entity.Comment;
import com.example.team8recipe.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class CommentViewController {

    private final CommentService commentService;


    @GetMapping("/comments") // 댓글 생성
    public String newComment(@RequestParam(required = false) Long id, Model model)
    {
        if(id == null ) {
            model.addAttribute("comment", new CommentViewResponseDto());
        }

        return "newComment";
    }

    //댓글 수정
    @PutMapping("/comments/{id}")
    public String updateComment(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("comment", new CommentViewResponseDto());
        } else {
            Comment comment = commentService.findById(id);
            model.addAttribute("commnet", new CommentViewResponseDto(comment));

        }
        return "update_Comment";
    }



}
