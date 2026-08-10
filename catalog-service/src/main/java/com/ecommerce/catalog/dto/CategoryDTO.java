package com.ecommerce.catalog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
@Data
public class CategoryDTO {
    private UUID id;

    @NotBlank(message = "Category name is required")
    private String name;
    private String description;
}
