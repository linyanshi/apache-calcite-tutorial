package com.github.linyanshi.delegation;

import com.github.linyanshi.api.Transformation;

import java.util.List;

/**
 * @author linyanshi
 * @version 2022-03-21
 */
public interface Executor {

    Pipeline createPipeline(List<Transformation<?>> transformations, String jobName);

    Object execute();

}
