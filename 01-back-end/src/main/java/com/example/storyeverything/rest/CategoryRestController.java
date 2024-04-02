package com.example.storyeverything.rest;

import com.example.storyeverything.dto.CategoryDTO;
import com.example.storyeverything.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryRestController {
    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> findAll(){
        List<CategoryDTO> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> find(@PathVariable long id){
        CategoryDTO category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryDTO> add(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO newCategory = categoryService.create(categoryDTO);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable long id, @RequestBody CategoryDTO categoryDTO){
        CategoryDTO updatedCategory = categoryService.update(id, categoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping ("/categories/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        if(categoryService.findById(id) == null)
            throw new RuntimeException("Category id not found - " + id);

        categoryService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
