package com.ecommerce.notification.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
    private UUID orderId;
    private String reference;
    private UUID customerId;
    private BigDecimal totalAmount;
}
