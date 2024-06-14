package com.example.storyeverything.rest;

import com.example.storyeverything.dto.CategoryDTO;
import com.example.storyeverything.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Category", description = "Category Management API")
public class CategoryRestController {
    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    @Operation(summary = "Get all categories", description = "Retrieve a list of all categories")
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/categories/{id}")
    @Operation(summary = "Get category by ID", description = "Retrieve a category by its ID")
    public ResponseEntity<CategoryDTO> find(@PathVariable long id) {
        CategoryDTO category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping("/categories")
    @Operation(summary = "Add new category", description = "Create a new category")
    public ResponseEntity<CategoryDTO> add(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryDTO newCategory = categoryService.create(categoryDTO);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }
}
