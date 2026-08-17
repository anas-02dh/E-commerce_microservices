package com.ecommerce.order.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
@Data
public class OrderRequest {
    private UUID customerId;
    private List<OrderLineRequest> orderLineRequests;
}
