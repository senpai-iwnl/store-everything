package com.example.storyeverything.service;

import com.example.storyeverything.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();
    CategoryDTO findById(long id);
    CategoryDTO create(CategoryDTO categoryDTO);
    CategoryDTO update(long id, CategoryDTO categoryDTO);
    void deleteById(long id);
}
