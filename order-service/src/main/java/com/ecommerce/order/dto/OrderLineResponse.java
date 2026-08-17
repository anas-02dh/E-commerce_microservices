package com.ecommerce.order.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
@Data
public class OrderLineResponse {
    private UUID id;
    private UUID productId;
    private Integer quantity;
    private BigDecimal unitPrice;

}
