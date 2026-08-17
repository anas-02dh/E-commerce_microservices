package com.ecommerce.order.service;

import com.ecommerce.order.dto.OrderRequest;
import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.order.entity.OrderStatus;

import java.util.List;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
public interface OrderService {
    OrderResponse create(OrderRequest orderRequest);
    List<OrderResponse> findAll();
    OrderResponse findById(UUID id);
    OrderResponse updateOrder(UUID id, OrderStatus status);
    void delete (UUID id);
}
