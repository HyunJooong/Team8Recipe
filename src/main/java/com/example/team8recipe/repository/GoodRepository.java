package com.example.team8recipe.repository;

import com.example.team8recipe.entity.Good;
import com.example.team8recipe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodRepository extends JpaRepository<Good, Long> {
    List<Good> findByUser(User user);
}
