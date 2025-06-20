package com.example.modashop.exception;


public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class<?> entityClass, Long id) {
        super("Could not find " + entityClass.getSimpleName() + " with id: " + id);
    }


}