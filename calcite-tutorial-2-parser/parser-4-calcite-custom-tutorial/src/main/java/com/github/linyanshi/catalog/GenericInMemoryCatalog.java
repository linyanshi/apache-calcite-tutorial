package com.github.linyanshi.catalog;

import com.github.linyanshi.ObjectPath;
import com.github.linyanshi.catalog.exceptions.CatalogException;
import com.github.linyanshi.catalog.exceptions.DatabaseNotExistException;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author linyanshi
 * @version 2022-03-21
 */
public class GenericInMemoryCatalog extends AbstractCatalog {

    public static final String DEFAULT_DB = "default";

    private final Map<String, CatalogDatabase> databases;
    private final Map<ObjectPath, CatalogBaseTable> tables;

    public GenericInMemoryCatalog(String name, String defaultDatabase) {
        super(name, defaultDatabase);

        this.databases = new LinkedHashMap<>();
        this.databases.put(defaultDatabase, new CatalogDatabaseImpl(new HashMap<>(), null));
        this.tables = new LinkedHashMap<>();
    }

    @Override
    public void open() throws CatalogException {
    }

    @Override
    public void close() throws CatalogException {

    }

    @Override
    public List<String> listDatabases() throws CatalogException {
        if (Objects.nonNull(databases)){
           return databases.keySet().stream().collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public boolean databaseExists(String databaseName) throws CatalogException {
        return false;
    }

    @Override
    public List<String> listTables(String databaseName) throws DatabaseNotExistException, CatalogException {
        //TODO 测试
        Set<ObjectPath> ops =
                tables.keySet().stream().filter(op -> op.getDatabaseName().equalsIgnoreCase(databaseName)).
                collect(Collectors.toSet());
        List<String> tbls = ops.stream().map(op -> tables.get(op)).map(catalogBaseTable -> catalogBaseTable.getTableName()).
                collect(Collectors.toList());
        return tbls;
    }

    @Override
    public boolean tableExists(ObjectPath tablePath) throws CatalogException {
        return false;
    }
}
