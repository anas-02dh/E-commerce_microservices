package com.ecommerce.catalog.dto;

import com.ecommerce.catalog.entity.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
@Data
public class ProductDTO {

    private UUID id;

    @NotBlank(message = "Product name is required")
    private String name;
    private String description;

    @NotNull(message = "Available quantity is required")
    @Min(value = 0, message = "Available quantity cannot be negative")
    private Integer availableQuantity;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = true,
            message = "Price cannot be negative")
    private BigDecimal price;

    @NotBlank(message = "Category name is required")
    private String categoryName;
}
