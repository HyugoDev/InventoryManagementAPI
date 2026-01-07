package com.inventory.management.exception;


public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
      super(message);
    }
    public ResourceNotFoundException(String message, Throwable cause) {}
}
