package com.ecommerce.customer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
@Data
public class AddressDTO {
    private UUID id;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "House number is required")
    private String houseNumber;

    @NotBlank(message = "Zip code is required")
    private String zipCode;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Country is required")
    private String country;
}
