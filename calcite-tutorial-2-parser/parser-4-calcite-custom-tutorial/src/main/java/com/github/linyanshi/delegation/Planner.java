package com.github.linyanshi.delegation;

import com.github.linyanshi.api.Transformation;
import com.github.linyanshi.opertions.Operation;

import java.util.List;

/**
 * @author linyanshi
 * @version 2022-03-21
 */
public interface Planner {

    Parser getParser();

    List<Transformation<?>> translate(List<Operation> operations);

}
