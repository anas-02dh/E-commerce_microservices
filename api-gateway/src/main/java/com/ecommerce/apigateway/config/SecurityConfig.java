package com.ecommerce.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author {ANAS DR}
 **/
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity http) {

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange

                        .pathMatchers("/actuator/**")
                        .permitAll()

                        // Catalog
                        .pathMatchers("/api/products/**")
                        .hasAnyRole("CUSTOMER", "ADMIN")

                        // Customers
                        .pathMatchers("/api/customers/**")
                        .hasAnyRole("CUSTOMER", "ADMIN")

                        // Create orders
                        .pathMatchers("/api/orders")
                        .hasRole("CUSTOMER")

                        // Order management
                        .pathMatchers("/api/orders/**")
                        .hasAnyRole("CUSTOMER", "ADMIN")

                        // Payments
                        .pathMatchers("/api/payments/**")
                        .hasAnyRole("CUSTOMER", "ADMIN")

                        .anyExchange()
                        .authenticated()
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                    jwt.jwtAuthenticationConverter(new KeycloakJwtConverter())
                        )
                )
                .build();
    }
}
