package com.ecommerce.order.client;

import com.ecommerce.order.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

/**
 * @author {ANAS DR}
 **/
@FeignClient(
        name = "catalog-service",
        url = "${application.config.catalog-url}"
)
public interface CatalogClient {

    @GetMapping("/api/products/{id}")
    ProductResponse findById(@PathVariable UUID id);
}
