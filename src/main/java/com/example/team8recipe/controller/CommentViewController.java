package com.example.team8recipe.controller;

import com.example.team8recipe.dto.CommentListViewResponseDto;
import com.example.team8recipe.dto.CommentResponseDto;
import com.example.team8recipe.dto.CommentViewResponseDto;
import com.example.team8recipe.entity.Comment;
import com.example.team8recipe.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class CommentViewController {

    private final CommentService commentService;


//    @PostMapping("/comments/{id}") // 댓글 생성
//    public String newComment(@RequestParam("id") Long id,@RequestParam("body") String body,
//                             @RequestParam("username") String userName, Model model)
//    {
//       if(id == null ) {
//            model.addAttribute("comment", new CommentViewResponseDto());
//        }
//
//        return "posts";
//    }

    @GetMapping("/comment/{id}") //댓글 하나 조회
    public String getComment(@PathVariable Long id,Model model){
        Comment comment =
                commentService.findById(id);
        model.addAttribute("comment",new CommentViewResponseDto(comment));

        return "posts";

    }

    //댓글 전체 조회
//    @GetMapping("/comments")
//    public String getComments(Model model){
//        List<CommentListViewResponseDto> commentListViewResponseDtos = commentService.findAll().stream()
//                .map(CommentListViewResponseDto::new)
//                .toList();
//        model.addAttribute("comments", commentListViewResponseDtos);
//        return "posts";
//    }



    //댓글 수정
    @PutMapping("/comments/{id}")
    public String updateComment(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("comment", new CommentViewResponseDto());
        } else {
            Comment comment = commentService.findById(id);
            model.addAttribute("commnet", new CommentViewResponseDto(comment));

        }
        return "posts";
    }



}