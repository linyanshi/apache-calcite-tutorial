package com.github.linyanshi.delegation;

import com.github.linyanshi.api.Transformation;

import java.util.List;

/**
 * @author linyanshi
 * @version 2022-03-21
 */
public class LysExecutor implements Executor{
    @Override
    public Pipeline createPipeline(List<Transformation<?>> transformations, String jobName) {
        return null;
    }

    @Override
    public Object execute() {
        return null;
    }
}
