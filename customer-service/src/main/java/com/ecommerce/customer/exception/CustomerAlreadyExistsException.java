package com.ecommerce.customer.exception;

/**
 * @author {ANAS DR}
 **/
public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
