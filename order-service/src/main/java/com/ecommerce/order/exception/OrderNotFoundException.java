package com.ecommerce.order.exception;

/**
 * @author {ANAS DR}
 **/
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
