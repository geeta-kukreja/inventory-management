package com.example.inventory_management.exception;


public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Long id) {
        super("Item with ID " + id + " not found.");
    }
}
