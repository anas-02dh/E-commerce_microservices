package com.ecommerce.order.dto;

import lombok.Data;

/**
 * @author {ANAS DR}
 **/

@Data
public class AddressResponse {
    private String street;

    private String houseNumber;

    private String zipCode;

    private String city;

    private String country;
}
