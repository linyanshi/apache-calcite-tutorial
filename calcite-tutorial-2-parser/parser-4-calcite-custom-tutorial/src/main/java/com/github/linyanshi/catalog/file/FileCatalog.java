package com.github.linyanshi.catalog.file;

import com.github.linyanshi.ObjectPath;
import com.github.linyanshi.catalog.AbstractCatalog;
import com.github.linyanshi.catalog.exceptions.CatalogException;

import java.util.ArrayList;
import java.util.List;

/**
 * 基于文件的catalog
 *
 * @author linyanshi
 * @version 2022-03-21
 */
public class FileCatalog extends AbstractCatalog {

    public FileCatalog(String name, String defaultDatabase) {
        super(name, defaultDatabase);
    }

    @Override
    public void open() throws CatalogException {
        System.out.println("file open");
    }

    @Override
    public void close() throws CatalogException {
        System.out.println("file close");
    }

    @Override
    public List<String> listDatabases() throws CatalogException {
        System.out.println("file listDatabases");
        return new ArrayList<>();
    }

    @Override
    public boolean databaseExists(String databaseName) throws CatalogException {
        System.out.println("file databaseExists");
        return true;
    }

    @Override
    public List<String> listTables(String databaseName) throws CatalogException {
        System.out.println("file listTables");
        return new ArrayList<>();
    }

    @Override
    public boolean tableExists(ObjectPath tablePath) throws CatalogException {
        System.out.println("file tableExists");
        return true;
    }
}
