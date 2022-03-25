package com.github.linyanshi.catalog.exceptions;

/**
 * @author linyanshi
 * @version 2022-03-21
 */
public class DatabaseNotExistException extends Exception {

    private static final long serialVersionUID = 6464655306054687105L;
    private static final String MSG = "Database %s does not exist in Catalog %s.";

    public DatabaseNotExistException(String catalogName, String databaseName, Throwable cause) {
        super(String.format(MSG, databaseName, catalogName), cause);
    }

    public DatabaseNotExistException(String catalogName, String databaseName) {
        this(catalogName, databaseName, null);
    }


}
