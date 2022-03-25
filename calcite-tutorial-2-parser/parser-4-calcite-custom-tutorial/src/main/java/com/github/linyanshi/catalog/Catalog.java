package com.github.linyanshi.catalog;

import com.github.linyanshi.ObjectPath;
import com.github.linyanshi.catalog.exceptions.CatalogException;
import com.github.linyanshi.catalog.exceptions.DatabaseNotExistException;

import java.util.List;

/**
 * Catalog的接口
 *
 * @author linyanshi
 * @version 2022-03-21
 */
public interface Catalog {


    void open() throws CatalogException;


    void close() throws CatalogException;


    String getDefaultDatabase() throws CatalogException;


    List<String> listDatabases() throws CatalogException;

    boolean databaseExists(String databaseName) throws CatalogException;

    List<String> listTables(String databaseName) throws DatabaseNotExistException, CatalogException;


    boolean tableExists(ObjectPath tablePath) throws CatalogException;

}
