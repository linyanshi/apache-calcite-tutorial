package com.github.linyanshi.delegation;

import com.github.linyanshi.catalog.CatalogManager;

/**
 * @author linyanshi
 * @version 2022-03-21
 */
public class PlannerFactory implements ComponentFactory {

    public static final String PLANNER_NAME = "planner";

    public Planner create(Executor executor, CatalogManager catalogManager) {
        return new LysPlanner(executor, catalogManager);
    }

    @Override
    public String name() {
        return PLANNER_NAME;
    }
}
