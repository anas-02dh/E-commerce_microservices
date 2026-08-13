package com.ecommerce.customer.repository;

import com.ecommerce.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    boolean existsByKeycloakUserId(String keycloakUserId);
    Optional<Customer> findByKeycloakUserId(String keycloakUserId);
}
