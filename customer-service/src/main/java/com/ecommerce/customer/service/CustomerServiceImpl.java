package com.ecommerce.customer.service;

import com.ecommerce.customer.dto.CustomerDTO;
import com.ecommerce.customer.entity.Customer;
import com.ecommerce.customer.exception.CustomerAlreadyExistsException;
import com.ecommerce.customer.exception.CustomerNotFoundException;
import com.ecommerce.customer.mapper.CustomerMapper;
import com.ecommerce.customer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author {ANAS DR}
 **/
@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{
    private final CustomerMapper mapper;
    private final CustomerRepository customerRepository;


    @Override
    public CustomerDTO create(CustomerDTO customerDTO, Jwt jwt
    ) {
        Customer customer = mapper.toEntity(customerDTO);

        String keycloakUserId = jwt.getSubject();

        if(customerRepository.existsByKeycloakUserId(keycloakUserId)) {
            throw new CustomerAlreadyExistsException(
                    "Customer already exists for this Keycloak user"
            );
        }

        customer.setKeycloakUserId(keycloakUserId);

        Customer savedCustomer = customerRepository.save(customer);

        return mapper.toDTO(savedCustomer);
    }



    @Override
    public CustomerDTO update(UUID id, CustomerDTO customerDTO, String keycloakUserId) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException(
                        "Customer not found with id: " + id
                )
        );

        if (!customer.getKeycloakUserId().equals(keycloakUserId)) {
            throw new AccessDeniedException(
                    "You cannot modify another customer's account"
            );
        }

        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.getAddress().setStreet(
                customerDTO.getAddress().getStreet()
        );
        customer.getAddress().setHouseNumber(
                customerDTO.getAddress().getHouseNumber()
        );

        customer.getAddress().setZipCode(
                customerDTO.getAddress().getZipCode()
        );

        customer.getAddress().setCity(
                customerDTO.getAddress().getCity()
        );

        customer.getAddress().setCountry(
                customerDTO.getAddress().getCountry()
        );


        Customer updatedCustomer = customerRepository.save(customer);


        return mapper.toDTO(updatedCustomer);
    }

    @Override
    public List<CustomerDTO> findAll() {

        //.map(customer -> mapper.toDTO(customer)
        //.map(mapper :: toDTO)
        return customerRepository.findAll().stream().map(mapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CustomerDTO findById(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException(
                        "Customer not found with id: " + id
                ));
        return mapper.toDTO(customer);
    }

    @Override
    public void delete(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException(
                        "Customer not found with id: " + id
                ));
        customerRepository.delete(customer);
    }

    @Override
    public CustomerDTO findMyCustomer(String keycloakUserId) {

        Customer customer= customerRepository.findByKeycloakUserId(keycloakUserId).orElseThrow(() ->
                new CustomerNotFoundException(
                        "Customer not found with keyCloakUserId: " +keycloakUserId
                        ));


        return mapper.toDTO(customer);
    }
}
