package com.ecommerce.order.client;

import com.ecommerce.order.config.FeignClientConfig;
import com.ecommerce.order.dto.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
@FeignClient(
        name = "customer-service",
        //url = "${application.config.customer-url}",
        configuration = FeignClientConfig.class
)

public interface CustomerClient {
    @GetMapping("/api/customers/{id}")
    CustomerResponse findCustomerById(
            @PathVariable UUID id
    );
}
