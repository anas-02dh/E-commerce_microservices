package com.ecommerce.order.dto;

import com.ecommerce.order.entity.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
@Data
public class OrderResponse {
    private UUID id;

    private UUID customerId;

    private LocalDateTime orderDate;

    private String reference;

    private OrderStatus status;

    private BigDecimal totalAmount;

    private List<OrderLineResponse> orderLines;
}
