package com.ecommerce.payment.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author {ANAS DR}
 **/
@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic paymentCompletedTopic() {
        return new NewTopic(
                "payment-completed",
                3,
                (short) 1
        );
    }
}
