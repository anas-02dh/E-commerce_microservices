package com.ecommerce.customer.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class KeycloakJwtAuthenticationConverter
        implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {

        Map<String, Object> realmAccess =
                jwt.getClaimAsMap("realm_access");

        Collection<SimpleGrantedAuthority> authorities =
                List.of();

        if (realmAccess != null) {

            Object roles = realmAccess.get("roles");

            if (roles instanceof Collection<?> roleCollection) {

                authorities = roleCollection.stream()
                        .map(Object::toString)
                        .map(role -> new SimpleGrantedAuthority(
                                "ROLE_" + role
                        ))
                        .collect(toList());
            }
        }

        return new JwtAuthenticationToken(jwt, authorities);
    }
}