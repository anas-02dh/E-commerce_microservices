package com.ecommerce.catalog.service;

import com.ecommerce.catalog.dto.CategoryDTO;
import com.ecommerce.catalog.entity.Category;
import com.ecommerce.catalog.exception.CategoryNotFoundException;
import com.ecommerce.catalog.mapper.CategoryMapper;
import com.ecommerce.catalog.mapper.ProductMapper;
import com.ecommerce.catalog.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author {ANAS DR}
 **/
@Service
@Transactional
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;


    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toEntity(categoryDTO);
        Category categorySaved = categoryRepository.save(category);
        return categoryMapper.toDTO(categorySaved);
    }

    @Override
    public CategoryDTO update(UUID id, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category ==  null) {
            throw  new CategoryNotFoundException("category not found");
        }

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        Category categoryUpdated = categoryRepository.save(category);

        return categoryMapper.toDTO(categoryUpdated);
    }


    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> categoryMapper.toDTO(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(UUID id) {

        Category category = categoryRepository.findById(id).orElse(null);
        if(category ==  null) {
            throw  new CategoryNotFoundException("category not found");
        }
        return categoryMapper.toDTO(category);
    }

    @Override
    public void delete(UUID id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category ==  null) {
            throw  new CategoryNotFoundException("category not found");
        }
        categoryRepository.delete(category);
    }
}
