package com.ecommerce.catalog.mapper;

import com.ecommerce.catalog.dto.ProductDTO;
import com.ecommerce.catalog.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author {ANAS DR}
 **/
@Component
public class ProductMapper {

    public ProductDTO toDTO(Product product) {
        if(product == null ){
            return null;
        }

        ProductDTO  productDTO = new ProductDTO();
        BeanUtils.copyProperties(product,productDTO);
        if(product.getCategory() != null) {
            productDTO.setCategoryName(product.getCategory().getName());
        }
        return productDTO;
    }

    public Product toEntity(ProductDTO productDTO) {
        if(productDTO == null) {
            return null;
        }
        Product product = new Product();
        BeanUtils.copyProperties(productDTO,product);
        return product;
    }
}
