package com.example.team8recipe.repository;

import com.example.team8recipe.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
