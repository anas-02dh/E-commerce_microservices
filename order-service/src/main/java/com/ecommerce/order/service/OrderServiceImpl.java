package com.ecommerce.order.service;

import com.ecommerce.order.client.CatalogClient;
import com.ecommerce.order.client.CustomerClient;
import com.ecommerce.order.dto.*;
import com.ecommerce.order.entity.Order;
import com.ecommerce.order.entity.OrderLine;
import com.ecommerce.order.entity.OrderStatus;
import com.ecommerce.order.exception.OrderNotFoundException;
import com.ecommerce.order.mapper.OrderMapper;
import com.ecommerce.order.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final CatalogClient catalogClient;
    private final CustomerClient customerClient;

    private String generateReference() {
        return "REF-" + UUID.randomUUID().toString().substring(0,0).toUpperCase();
    }

    private OrderLine createOrderLine(OrderLineRequest request, Order order) {
        ProductResponse productResponse = catalogClient.findById(request.getProductId());
        if (productResponse.getAvailableQuantity() < request.getQuantity()) {
            throw new IllegalStateException(
                    "Insufficient stock for product: " + productResponse.getName()
            );
        }

        return OrderLine.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .unitPrice(productResponse.getPrice())
                .order(order).build();
    }
    @Override
    public OrderResponse create(OrderRequest orderRequest) {

        CustomerResponse customerResponse = customerClient.findCustomerById(orderRequest.getCustomerId());

        Order order = Order.builder()
                .customerId(customerResponse.getId())
                .orderDate(LocalDateTime.now())
                .reference(generateReference())
                .status(OrderStatus.PENDING)
                .totalAmount(BigDecimal.ZERO)
                .build();

        List<OrderLine> orderLines = orderRequest.getOrderLineRequests().stream().map(lineRequest-> createOrderLine(lineRequest,order)).toList();
        order.setOrderLines(orderLines);

        BigDecimal total = orderLines.stream().map(orderLine -> orderLine.getUnitPrice().multiply(BigDecimal.valueOf(orderLine.getQuantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);;

        order.setTotalAmount(total);


        return mapper.toResponse(orderRepository.save(order));
    }

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public OrderResponse findById(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new OrderNotFoundException(
                        "Order not found with id: " + id
                )
        );
        return mapper.toResponse(order);
    }

    @Override
    public OrderResponse updateOrder(UUID id, OrderStatus status) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new OrderNotFoundException(
                        "Order not found with id: " + id
                )
        );

        order.setStatus(status);

        orderRepository.save(order);

        return mapper.toResponse(order);
    }

    @Override
    public void delete(UUID id) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new OrderNotFoundException(
                        "Order not found with id: " + id
                )
        );

        orderRepository.delete(order);
    }
}
