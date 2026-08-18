package com.ecommerce.order.producer;

import com.ecommerce.order.event.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author {ANAS DR}
 **/
@Component
@RequiredArgsConstructor
public class OrderEventProducer {
    private static final String ORDER_CREATED_TOPIC = "order-created";

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {

        kafkaTemplate.send(
                ORDER_CREATED_TOPIC,
                event.getOrderId().toString(),
                event
        );

    }
}