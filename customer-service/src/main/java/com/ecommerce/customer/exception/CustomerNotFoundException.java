package com.ecommerce.customer.exception;

/**
 * @author {ANAS DR}
 **/
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
