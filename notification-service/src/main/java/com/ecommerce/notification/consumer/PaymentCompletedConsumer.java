package com.ecommerce.notification.consumer;

import com.ecommerce.notification.event.PaymentCompletedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author {ANAS DR}
 **/

@Component
@Slf4j
public class PaymentCompletedConsumer {
    @KafkaListener(
            topics = "payment-completed",
            groupId = "notification-service"
    )
    public void consume(PaymentCompletedEvent event) {
        log.info("Payment confirmation received:");
        log.info("orderId={}", event.getOrderId());
        log.info("reference={}", event.getReference());
        log.info("customerId={}", event.getCustomerId());
        log.info("amount={}", event.getAmount());
        log.info("status={}", event.getStatus());
    }
}
