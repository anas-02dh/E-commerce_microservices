package com.ecommerce.order.mapper;

import com.ecommerce.order.dto.OrderLineResponse;
import com.ecommerce.order.dto.OrderResponse;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderLine;
import org.mapstruct.Mapper;

/**
 * @author {ANAS DR}
 **/
@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toResponse(Order order);
    OrderLineResponse toResponse(OrderLine orderLine);
}
