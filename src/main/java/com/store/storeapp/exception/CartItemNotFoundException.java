package com.store.storeapp.exception;

public class CartItemNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CartItemNotFoundException(Long id) {
        super("Could not find cart item " + id);
    }
}
