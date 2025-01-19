package com.example.bai_tap.service;

import com.example.bai_tap.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBlogService extends IService<Blog> {
    Page<Blog> getAll(Pageable pageable);
    Page<Blog> searchByTitle(String title, Pageable pageable);
    Page<Blog> getBlogsByCategory(int categoryId, Pageable pageable);
}
