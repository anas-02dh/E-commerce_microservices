package com.ecommerce.catalog.service;

import com.ecommerce.catalog.dto.CategoryDTO;

import java.util.List;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
public interface CategoryService {
    CategoryDTO create(CategoryDTO categoryDTO);
    CategoryDTO update(UUID id,CategoryDTO categoryDTO);
    List<CategoryDTO> findAll();
    CategoryDTO findById(UUID id);
    void delete(UUID id);

}
