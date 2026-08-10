package com.ecommerce.catalog.exception;

/**
 * @author {ANAS DR}
 **/
public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
