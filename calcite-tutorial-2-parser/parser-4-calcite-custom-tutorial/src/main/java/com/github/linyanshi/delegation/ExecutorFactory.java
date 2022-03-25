package com.github.linyanshi.delegation;

/**
 * @author linyanshi
 * @version 2022-03-21
 */
public class ExecutorFactory implements ComponentFactory {

    public static final String EXECUTOR_NAME = "executor";

    public Executor create() {
        return new LysExecutor();
    }

    @Override
    public String name() {
        return EXECUTOR_NAME;
    }
}
