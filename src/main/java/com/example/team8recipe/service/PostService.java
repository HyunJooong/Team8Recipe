package com.example.team8recipe.service;

import com.example.team8recipe.dto.PostListResponseDto;
import com.example.team8recipe.dto.PostRequestDto;
import com.example.team8recipe.dto.PostResponseDto;
import com.example.team8recipe.entity.Post;
import com.example.team8recipe.entity.User;
import com.example.team8recipe.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto);
        post.setUser(user);

        postRepository.save(post);

        return new PostResponseDto(post);
    }

    public PostListResponseDto getPosts() {
        List<PostResponseDto> postList = postRepository.findAll().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());

        return new PostListResponseDto(postList);
    }

    public PostResponseDto getPostById(Long id) {
        Post post = findPost(id);

        return new PostResponseDto(post);
    }

    public void deletePost(Long id, User user) {
        Post post = findPost(id);

        // 게시글 작성자(post.user) 인지 체크 (아니면 예외발생)
        if (!post.getUser().getUserId().equals(user.getUserId())) {
            throw new RejectedExecutionException();
        }

        postRepository.delete(post);
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, User user) {
        Post post = findPost(id);
        System.out.println("Service // post.getUser() = " + post.getUser().getUserId());
        // 게시글 작성자(post.user) 인지 체크 (아니면 예외발생)
        if (!post.getUser().getUserId().equals(user.getUserId())) {
            throw new RejectedExecutionException();
        }

        post.setTitle(requestDto.getTitle());
        post.setContent(requestDto.getContent());

        return new PostResponseDto(post);
    }

    public Post findPost(long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.")
        );
    }
}
