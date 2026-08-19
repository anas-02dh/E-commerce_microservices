package com.ecommerce.payment.consume;

import com.ecommerce.payment.event.OrderCreatedEvent;
import com.ecommerce.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author {ANAS DR}
 **/

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedConsumer {
    private final PaymentService paymentService;

    @KafkaListener(
            topics = "order-created",
            groupId = "payment-service"
    )

    public void consume(OrderCreatedEvent event) {
        log.info("Order created event received by payment-service: {}",
                event.getOrderId());
        paymentService.processPayment(event);
    }
}
