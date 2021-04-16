package com.store.storeapp.exception;

public class JournalNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    public JournalNotFoundException(Long id) {
        super("Could not find Journal " + id);
    }
}
