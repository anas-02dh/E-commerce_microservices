package com.ecommerce.order.repository;

import com.ecommerce.order.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
public interface OrderLineRepository extends JpaRepository<OrderLine, UUID> {
}
