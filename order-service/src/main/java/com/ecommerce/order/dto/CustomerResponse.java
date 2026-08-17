package com.ecommerce.order.dto;

import lombok.Data;

import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
@Data
public class CustomerResponse {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private AddressResponse address;
}
