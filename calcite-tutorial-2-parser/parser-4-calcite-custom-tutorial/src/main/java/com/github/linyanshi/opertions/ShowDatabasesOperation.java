package com.github.linyanshi.opertions;

/**
 * @author linyanshi
 * @version 2022-03-21
 */
public class ShowDatabasesOperation implements ShowOperation{

    @Override
    public String asSummaryString() {
        return "SHOW DATABASES";
    }

}
