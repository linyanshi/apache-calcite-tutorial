package com.github.linyanshi.delegation;

import com.github.linyanshi.opertions.Operation;

import java.util.List;

/**
 * @author linyanshi
 * @version 2022-03-21
 */
public interface Parser {

    List<Operation> parse(String statement);

}
