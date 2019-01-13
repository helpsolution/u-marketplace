package com.reljicd.service;

import com.reljicd.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category findById(Long id);
    List<Category> findAllCategory();

}
