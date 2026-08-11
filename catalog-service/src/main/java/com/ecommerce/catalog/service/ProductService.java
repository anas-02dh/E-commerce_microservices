package com.ecommerce.catalog.service;

import com.ecommerce.catalog.dto.CategoryDTO;
import com.ecommerce.catalog.dto.ProductDTO;
import com.ecommerce.catalog.dto.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
public interface ProductService {
    ProductDTO create(ProductDTO productDTO);
    ProductDTO update(UUID id, ProductDTO productDTO);
    Page<ProductDTO> findAll(ProductFilter filter, Pageable pageable);
    ProductDTO findById(UUID id);
    //List<ProductDTO> findByCategoryName(String categoryName);
    void delete(UUID id);
    List<ProductDTO> searchProducts(String keyword);
}
