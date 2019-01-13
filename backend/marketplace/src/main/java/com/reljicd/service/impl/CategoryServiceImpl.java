package com.reljicd.service.impl;

import com.reljicd.model.Category;
import com.reljicd.model.Product;
import com.reljicd.repository.CategoryRepository;
import com.reljicd.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }
}
