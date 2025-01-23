package com.example.bai_tap.service.impl;

import com.example.bai_tap.exception.ResourceNotFoundException;
import com.example.bai_tap.model.Category;
import com.example.bai_tap.repository.ICategoryRepository;
import com.example.bai_tap.service.ICategoryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void addNew(Category category) {
        categoryRepository.save(category);

    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);

    }

    @Override
    public void update(Category category) {
        categoryRepository.save(category);

    }

    @Override
    public Category getById(int id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with ID " + id + " not found."));
    }
}
