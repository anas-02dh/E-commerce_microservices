package com.ecommerce.customer.repository;

import com.ecommerce.customer.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
public interface AddressRepository extends JpaRepository<Address, UUID> {

}
