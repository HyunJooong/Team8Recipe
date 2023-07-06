package com.example.team8recipe.controller;

import com.example.team8recipe.dto.*;
import com.example.team8recipe.security.UserDetailsImpl;
import com.example.team8recipe.service.PostService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    //게시글 작성
    @PostMapping("/posts")
    public ResponseEntity<PostResponseDto> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto requestDto) {
        PostResponseDto result = postService.createPost(requestDto, userDetails.getUser());

        return ResponseEntity.status(201).body(result);
    }

    @GetMapping("/insert-posts")
    public String userSignup(){
        return "newposts";
    }

    //전체 게시글 조회
    @GetMapping("/posts")
    public ResponseEntity<PostListResponseDto> getPosts(Model model) throws JsonProcessingException {
        PostListResponseDto result = postService.getPosts();

        return ResponseEntity.ok().body(result);
    }

    //선택 게시글 조회
    @GetMapping("/posts/{id}")
    public String getPostById(@PathVariable Long id,Model model) {
        PostResponseDto result = postService.getPostById(id);
        model.addAttribute("post", result);
        return "posts";
    }

    //게시글 수정
    @PutMapping("/posts/{id}")
    public ResponseEntity<ApiResponseDto> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody PostRequestDto requestDto) {

        System.out.println("id = " + id);


        try {
            PostResponseDto result = postService.updatePost(id, requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(result);
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 수정 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PostMapping("/edit-page")
    public String userEdit(@ModelAttribute InsertDto requestDto, Model model){

        model.addAttribute("data",requestDto);

        return "postEdit";
    }

    //게시글 삭제
    @DeleteMapping("/posts/{id}")
    public ResponseEntity<ApiResponseDto> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        try {
            postService.deletePost(id, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("게시글 삭제 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 삭제 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }
}
