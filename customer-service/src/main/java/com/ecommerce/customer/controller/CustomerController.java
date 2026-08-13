package com.ecommerce.customer.controller;

import com.ecommerce.customer.dto.CustomerDTO;
import com.ecommerce.customer.entity.Customer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import com.ecommerce.customer.service.CustomerService;

import java.util.List;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;


    @GetMapping
    public List<CustomerDTO> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public CustomerDTO findById(@PathVariable UUID id){
        return customerService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO create(@Valid @RequestBody CustomerDTO customerDTO,  @AuthenticationPrincipal Jwt jwt) {
        return customerService.create(customerDTO, jwt);
    }

    @PutMapping("/{id}")
    public CustomerDTO update(@PathVariable UUID id, @Valid @RequestBody CustomerDTO customerDTO){
        return customerService.update(id,customerDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        customerService.delete(id);
    }
}
