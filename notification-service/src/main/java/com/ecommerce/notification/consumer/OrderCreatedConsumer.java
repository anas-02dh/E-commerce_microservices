package com.ecommerce.notification.consumer;

import com.ecommerce.notification.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author {ANAS DR}
 **/
@Component
@Slf4j
public class OrderCreatedConsumer {
    @KafkaListener(
            topics = "order-created",
            groupId = "notification-service"
    )

    public void consume(OrderCreatedEvent event) {

        log.info(
                "Order confirmation received: orderId={}, reference={}, customerId={}, totalAmount={}",
                event.getOrderId(),
                event.getReference(),
                event.getCustomerId(),
                event.getTotalAmount()
        );
    }

}
