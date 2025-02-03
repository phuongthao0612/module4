package com.example.bai_tap.repository;

import com.example.bai_tap.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBlogRepository extends JpaRepository <Blog, Integer> {
    Page<Blog> findByTitleContaining(String title, Pageable pageable);

    Page<Blog> findByCategory_Id(int categoryId, Pageable pageable);

    Page<Blog> findByCategoryNameContaining(String categoryName, Pageable pageable);
}
