package com.ecommerce.payment.producer;

import com.ecommerce.payment.event.PaymentCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author {ANAS DR}
 **/

@Component
@RequiredArgsConstructor
public class PaymentEventProducer {
    private static final String PAYMENT_COMPLETED_TOPIC = "payment-completed";
    private final KafkaTemplate<String, PaymentCompletedEvent> kafkaTemplate;

    public void sendPaymentCompletedEvent(PaymentCompletedEvent event) {
        kafkaTemplate.send(
                PAYMENT_COMPLETED_TOPIC,
                event.getOrderId().toString(),
                event
        );

    }
}
