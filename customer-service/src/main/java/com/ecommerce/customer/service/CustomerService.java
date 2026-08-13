package com.ecommerce.customer.service;

import com.ecommerce.customer.dto.CustomerDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
public interface CustomerService {
    CustomerDTO create(CustomerDTO customerDTO, Jwt jwt);
    CustomerDTO update(UUID id, CustomerDTO customerDTO);
    List<CustomerDTO> findAll();
    CustomerDTO findById(UUID id);
    void delete(UUID id);
}
