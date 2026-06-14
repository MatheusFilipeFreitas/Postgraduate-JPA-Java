package com.mathffreitas.jpaunipds.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidHeaderException extends AuthenticationException {

    public InvalidHeaderException(String message) {
        super(message);
    }
}
