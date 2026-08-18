package com.ecommerce.order.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NativeGenerator;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCreatedEvent {
    private UUID orderId;

    private String reference;

    private UUID customerId;

    private BigDecimal totalAmount;
}
