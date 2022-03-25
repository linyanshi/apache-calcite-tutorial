package com.github.linyanshi.catalog.exceptions;

/**
 * @author linyanshi
 * @version 2022-03-21
 */
public class CatalogException extends RuntimeException {
    private static final long serialVersionUID = 3266235542893712987L;

    public CatalogException(String message) {
        super(message);
    }

    public CatalogException(Throwable cause) {
        super(cause);
    }

    public CatalogException(String message, Throwable cause) {
        super(message, cause);
    }


}
