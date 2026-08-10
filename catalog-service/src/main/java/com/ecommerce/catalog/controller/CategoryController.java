package com.ecommerce.catalog.controller;

import com.ecommerce.catalog.dto.CategoryDTO;
import com.ecommerce.catalog.dto.ProductDTO;
import com.ecommerce.catalog.entity.Category;
import com.ecommerce.catalog.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDTO> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public  CategoryDTO findById(@PathVariable UUID id){
        return categoryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO create(@Valid @RequestBody CategoryDTO categoryDTO){
        return categoryService.create(categoryDTO);
    }

    @PutMapping("/{id}")
    public CategoryDTO update(@PathVariable UUID id, @Valid @RequestBody CategoryDTO categoryDTO) {
        return categoryService.update(id,categoryDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        categoryService.delete(id);
    }

}
