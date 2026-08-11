package com.ecommerce.catalog.service;

import com.ecommerce.catalog.dto.ProductDTO;
import com.ecommerce.catalog.dto.ProductFilter;
import com.ecommerce.catalog.entity.Category;
import com.ecommerce.catalog.entity.Product;
import com.ecommerce.catalog.exception.ProductNotFoundException;
import com.ecommerce.catalog.mapper.ProductMapper;
import com.ecommerce.catalog.repository.CategoryRepository;
import com.ecommerce.catalog.repository.ProductRepository;
import com.ecommerce.catalog.specification.ProductSpecification;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
public class ProductServiceImpl implements ProductService {
    private ProductMapper productMapper;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    @Override
    public ProductDTO create(ProductDTO productDTO) {

        Category category = categoryRepository.findByName(productDTO.getCategoryName()).orElse(null);

        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setAvailableQuantity(productDTO.getAvailableQuantity());
        product.setPrice(productDTO.getPrice());
        product.setCategory(category);

        Product productSaved = productRepository.save(product);
        return productMapper.toDTO(productSaved);

    }

    @Override
    public ProductDTO update(UUID id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            throw  new ProductNotFoundException("Product not found");
        }
        Category category = categoryRepository.findByName(productDTO.getCategoryName()).orElseThrow(() ->
                new RuntimeException("Category not found")
        );

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setAvailableQuantity(product.getAvailableQuantity());
        product.setPrice(product.getPrice());
        product.setCategory(category);

        Product productUpdated= productRepository.save(product);

        return productMapper.toDTO(productUpdated);

    }

    @Override
    public Page<ProductDTO> findAll(ProductFilter productFilter, Pageable pageable) {
        // find in database all products which describes the conditions(filters)
        Specification<Product> specification = ProductSpecification.withFilters(productFilter);

        return productRepository.findAll(specification,pageable).map(productMapper::toDTO);

        //List<Product> products = productRepository.findAll();
        //return products.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(UUID id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            throw new ProductNotFoundException("product not found");
        }

        return productMapper.toDTO(product);
    }

    /*@Override
    public List<ProductDTO> findByCategoryName(String categoryName) {

        Category category = categoryRepository.findByName(categoryName).orElse(null);
        List<Product> products = productRepository.findAllByCategory(category);


        return products.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
    }

    */
    @Override
    public void delete(UUID id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            throw new ProductNotFoundException("product not found");
        }
        productRepository.delete(product);
    }

    @Override
    public List<ProductDTO> searchProducts(String keyword) {
        List<Product> products= productRepository.findByNameContainsIgnoreCase(keyword);
        return products.stream().map(product -> productMapper.toDTO(product)).collect(Collectors.toList());
    }
}
