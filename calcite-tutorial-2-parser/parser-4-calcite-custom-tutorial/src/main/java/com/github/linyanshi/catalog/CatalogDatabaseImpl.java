package com.github.linyanshi.catalog;

import java.util.Map;

/**
 * @author linyanshi
 * @version 2022-03-21
 */
public class CatalogDatabaseImpl implements CatalogDatabase {

    private final String comment;

    public CatalogDatabaseImpl(Map<String, String> properties, String comment) {
        this.comment = comment;
    }

    @Override
    public String getComment() {
        return comment;
    }

}
