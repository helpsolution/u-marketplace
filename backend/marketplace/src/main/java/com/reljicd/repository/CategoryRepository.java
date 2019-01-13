package com.reljicd.repository;

import com.reljicd.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findById(Long id);
    List<Category> findAll();
}
