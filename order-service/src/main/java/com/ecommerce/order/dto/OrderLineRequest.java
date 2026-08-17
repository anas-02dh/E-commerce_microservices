package com.ecommerce.order.dto;

import lombok.Data;

import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
@Data
public class OrderLineRequest {
    private UUID productId;
    private Integer quantity;

}
