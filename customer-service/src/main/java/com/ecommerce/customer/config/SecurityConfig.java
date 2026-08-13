package com.ecommerce.customer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author {ANAS DR}
 **/
@Configuration

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // REST Api -> disable CSRF
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Swagger Open api
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                ).permitAll()
                        // Customer API requires authentication
                                .requestMatchers(HttpMethod.GET, "/api/customers/**")
                                .hasAnyRole("CUSTOMER", "ADMIN")

                                .requestMatchers(HttpMethod.POST, "/api/customers")
                                .hasRole("CUSTOMER")

                                .requestMatchers(HttpMethod.PUT, "/api/customers/**")
                                .hasRole("CUSTOMER")

                                .requestMatchers(HttpMethod.DELETE, "/api/customers/**")
                                .hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                // Validate Bearer JWT
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(jwt ->
                                jwt.jwtAuthenticationConverter(
                                        new KeycloakJwtAuthenticationConverter()
                                )
                        )
                );
        return http.build();
    }

}
