package com.example.storyeverything.service.impl;

import com.example.storyeverything.dto.CategoryDTO;
import com.example.storyeverything.exception.FieldNotFoundException;
import com.example.storyeverything.mapper.CategoryMapper;
import com.example.storyeverything.model.Category;
import com.example.storyeverything.repository.CategoryRepository;
import com.example.storyeverything.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toDTOList(categories);
    }

    @Override
    public CategoryDTO findById(long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("Category", "id", id));
        return categoryMapper.toDTO(category);
    }

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        category = categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }

    @Override
    @Transactional
    public CategoryDTO update(long id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("Category", "id", id));
        categoryMapper.updateCategoryFromDTO(categoryDTO, category);
        return categoryMapper.toDTO(category);
    }

    @Override
    public void deleteById(long id) {
        if(categoryRepository.findById(id) == null)
            throw new FieldNotFoundException("Category", "id", id);

        categoryRepository.deleteById(id);
    }
}
