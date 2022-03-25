package com.github.linyanshi.catalog.exceptions;

/**
 * @author linyanshi
 * @version 2022-03-21
 */
public class ValidationException extends RuntimeException {

    private static final long serialVersionUID = -2082275573115478881L;

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(String message) {
        super(message);
    }
}
