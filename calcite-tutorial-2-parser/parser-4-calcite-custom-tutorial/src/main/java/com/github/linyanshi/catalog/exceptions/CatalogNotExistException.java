package com.github.linyanshi.catalog.exceptions;

/**
 * @author linyanshi
 * @version 2022-03-21
 */
public class CatalogNotExistException extends RuntimeException {

    private static final long serialVersionUID = -2586018452550501186L;

    public CatalogNotExistException(String catalogName) {
        this(catalogName, null);
    }

    public CatalogNotExistException(String catalogName, Throwable cause) {
        super("Catalog " + catalogName + " does not exist.", cause);
    }
}
