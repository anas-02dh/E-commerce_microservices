package com.ecommerce.order.repository;

import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
public interface OrderRepository extends JpaRepository<Order, UUID> {
    boolean existsByReference(String reference);
}
