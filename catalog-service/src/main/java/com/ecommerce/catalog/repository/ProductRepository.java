package com.ecommerce.catalog.repository;

import com.ecommerce.catalog.entity.Category;
import com.ecommerce.catalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
public interface ProductRepository extends JpaRepository<Product, UUID> , JpaSpecificationExecutor<Product> {
    List<Product> findAllByCategory(Category category);

    List<Product> findByNameContainsIgnoreCase(String name);
}
