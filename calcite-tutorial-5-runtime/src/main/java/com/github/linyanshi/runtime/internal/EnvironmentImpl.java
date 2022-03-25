package com.github.linyanshi.runtime.internal;

import com.github.linyanshi.catalog.CatalogManager;
import com.github.linyanshi.catalog.GenericInMemoryCatalog;
import com.github.linyanshi.delegation.Executor;
import com.github.linyanshi.delegation.ExecutorFactory;
import com.github.linyanshi.delegation.Parser;
import com.github.linyanshi.delegation.Planner;
import com.github.linyanshi.delegation.PlannerFactory;
import com.github.linyanshi.factories.ComponentFactoryService;
import com.github.linyanshi.opertions.Operation;
import com.github.linyanshi.opertions.ShowDatabasesOperation;
import com.github.linyanshi.runtime.Environment;
import com.github.linyanshi.runtime.EnvironmentSettings;

import java.util.List;

/**
 * @author zhangap
 * @version 1.0, 2021/4/30
 */
public class EnvironmentImpl implements Environment {

    private final CatalogManager catalogManager;
    protected final Parser parser;

    public EnvironmentImpl(CatalogManager catalogManager,
                           Executor executor,
                           Planner planner) {
        this.catalogManager = catalogManager;

        this.parser = planner.getParser();
    }

    public static EnvironmentImpl create(EnvironmentSettings settings) {
        // 创建catalogManager
        CatalogManager catalogManager =
                CatalogManager.newBuilder()
                        .defaultCatalog(
                                settings.getBuiltInCatalogName(),
                                new GenericInMemoryCatalog(
                                        settings.getBuiltInCatalogName(),
                                        settings.getBuiltInDatabaseName()))
                        .build();

        ExecutorFactory executorFactory = ComponentFactoryService.find(ExecutorFactory.EXECUTOR_NAME);
        Executor executor = executorFactory.create();

        PlannerFactory plannerFactory = ComponentFactoryService.find(PlannerFactory.PLANNER_NAME);
        Planner planner = plannerFactory.create(executor, catalogManager);

        return new EnvironmentImpl(catalogManager, executor, planner);
    }

    /**
     * 执行SQL
     */
    public void executeSql(String query) {
        List<Operation> operations = parser.parse(query);
        if (operations.size() != 1) {
            throw new IllegalStateException("一次只能执行一条sql语句");
        }

        // 执行operation
        executeOperation(operations.get(0));
    }

    /**
     * 执行operation
     */
    private void executeOperation(Operation operation) {
        if (operation instanceof ShowDatabasesOperation) {
            buildShowResult("database name", listDatabases());
        }
    }

    /**
     * 输出结果
     */
    private void buildShowResult(String columnName, String[] objects) {
        System.out.println(columnName+": ");
        for (String object : objects) {
            System.out.println(object);
        }
    }

    private String[] listDatabases() {
        return catalogManager.getCatalog(catalogManager.getCurrentCatalog())
                .get()
                .listDatabases()
                .toArray(new String[0]);
    }

}
