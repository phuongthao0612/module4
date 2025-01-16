package com.example.bai_tap.service.impl;

import com.example.bai_tap.model.Blog;
import com.example.bai_tap.repository.IBlogRepository;
import com.example.bai_tap.service.IBlogService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class BlogService implements IBlogService {
    @Autowired
    private IBlogRepository blogRepository;
    @Override
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    @Override
    public void addNew(Blog blog) {
        blogRepository.save(blog);

    }

    @Override
    public void delete(Blog blog) {
        blogRepository.delete(blog);
    }

    @Override
    public void update(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public Blog getById(int id) {
        return blogRepository.findById(id).get();
    }

}
