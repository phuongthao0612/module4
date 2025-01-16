package com.example.bai_tap.repository;

import com.example.bai_tap.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBlogRepository extends JpaRepository <Blog, Integer> {
}
