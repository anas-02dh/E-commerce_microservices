package com.ecommerce.payment.service;

import com.ecommerce.payment.event.OrderCreatedEvent;
import com.ecommerce.payment.event.PaymentCompletedEvent;
import com.ecommerce.payment.producer.PaymentEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author {ANAS DR}
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final PaymentEventProducer paymentEventProducer;

    public void processPayment(OrderCreatedEvent event) {
        log.info(
                "Processing payment for order={}, amount={}",
                event.getOrderId(),
                event.getTotalAmount()
        );

        String paymentStatus = "COMPLETED";

        PaymentCompletedEvent paymentEvent =
                PaymentCompletedEvent.builder()
                        .orderId(event.getOrderId())
                        .reference(event.getReference())
                        .customerId(event.getCustomerId())
                        .amount(event.getTotalAmount())
                        .status(paymentStatus)
                        .build();

        paymentEventProducer.sendPaymentCompletedEvent(paymentEvent);

        log.info(
                "Payment completed for order={}",
                event.getOrderId()
                );
    }
}
