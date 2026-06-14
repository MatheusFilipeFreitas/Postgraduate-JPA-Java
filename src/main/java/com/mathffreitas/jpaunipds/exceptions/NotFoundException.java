package com.mathffreitas.jpaunipds.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private final String entityName;
    private final Object id;

    public NotFoundException(String entityName, Object id) {
        super(entityName + " not found: " + id);
        this.entityName = entityName;
        this.id = id;
    }
}
