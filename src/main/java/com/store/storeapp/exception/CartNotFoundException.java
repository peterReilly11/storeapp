package com.store.storeapp.exception;

public class CartNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public CartNotFoundException(Long id) {
        super("Could not find cart " + id);
    }
}
