package com.ecommerce.order.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/

@Data
public class ProductResponse {
    private UUID id;

    private String name;

    private String description;

    private Integer availableQuantity;

    private BigDecimal price;

    private String categoryName;
}
