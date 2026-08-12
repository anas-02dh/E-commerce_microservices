package com.ecommerce.customer.service;

import com.ecommerce.customer.dto.CustomerDTO;

import java.util.List;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
public interface CustomerService {
    CustomerDTO create(CustomerDTO customerDTO);
    CustomerDTO update(UUID id, CustomerDTO customerDTO);
    List<CustomerDTO> findAll();
    CustomerDTO findById(UUID id);
    void delete(UUID id);
}
