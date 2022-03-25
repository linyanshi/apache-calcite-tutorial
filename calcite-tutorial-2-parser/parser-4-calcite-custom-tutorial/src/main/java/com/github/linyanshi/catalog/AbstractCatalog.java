package com.github.linyanshi.catalog;

/**
 * @author linyanshi
 * @version 2022-03-21
 */
public abstract class AbstractCatalog implements Catalog {
    private final String catalogName;
    private final String defaultDatabase;

    public AbstractCatalog(String name, String defaultDatabase) {
        this.catalogName = name;
        this.defaultDatabase = defaultDatabase;
    }


    public String getName() {
        return catalogName;
    }

    @Override
    public String getDefaultDatabase() {
        return defaultDatabase;
    }

}
