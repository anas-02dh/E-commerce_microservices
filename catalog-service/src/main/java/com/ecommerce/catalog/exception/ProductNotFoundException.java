package com.ecommerce.catalog.exception;

/**
 * @author {ANAS DR}
 **/
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
