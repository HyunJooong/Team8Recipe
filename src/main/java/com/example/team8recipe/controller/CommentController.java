package com.example.team8recipe.controller;

import com.example.team8recipe.dto.ApiResponseDto;
import com.example.team8recipe.dto.CommentRequestDto;
import com.example.team8recipe.dto.CommentResponseDto;
import com.example.team8recipe.security.UserDetailsImpl;
import com.example.team8recipe.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments") // 댓글 생성
    public ResponseEntity<CommentResponseDto> createComment(@AuthenticationPrincipal
                                                            UserDetailsImpl userDetails,
                                                            @RequestBody CommentRequestDto requestDto){

        CommentResponseDto commentResult = commentService.createComment(requestDto,userDetails.getUser());

        return ResponseEntity.status(HttpStatus.CREATED).body(commentResult);
    }




    @PutMapping("/comments/{id}") // 댓글 수정
    public ResponseEntity<ApiResponseDto> updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        try {
            CommentResponseDto commentResult = commentService.updateComment(id, requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(commentResult);

        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 수정 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @DeleteMapping("/comments/{id}") //댓글 삭제
    public ResponseEntity<ApiResponseDto> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        try {
            commentService.deleteComment(id, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("댓글 삭제 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 삭제 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

}
