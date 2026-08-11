package com.ecommerce.catalog.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author {ANAS DR}
 **/
@Data
public class ProductFilter {
    private String name;
    private String categoryName;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

}
