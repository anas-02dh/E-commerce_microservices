package com.ecommerce.catalog.controller;

import com.ecommerce.catalog.dto.ProductDTO;
import com.ecommerce.catalog.dto.ProductFilter;
import com.ecommerce.catalog.entity.Product;
import com.ecommerce.catalog.service.ProductService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductDTO> findAll(ProductFilter productFilter, Pageable pageable) {
        return productService.findAll(productFilter,pageable);
    }

    @GetMapping("/{id}")
    public ProductDTO findById(@PathVariable UUID id) {
        return productService.findById(id);
    }

    /*
    @GetMapping("{categoryName}")
    public List<ProductDTO> findByName(@PathVariable String categoryName){
        return productService.findByCategoryName(categoryName);
    }
   */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@Valid @RequestBody ProductDTO productDTO) {
        return productService.create(productDTO);
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable UUID id, @Valid @RequestBody ProductDTO productDTO) {
        return productService.update(id,productDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        productService.delete(id);
    }

    @GetMapping("/search")
    public List<ProductDTO> searchProducts(@RequestParam(name = "keyword",defaultValue = "")String keyword) {
        return productService.searchProducts(keyword);
    }


}
