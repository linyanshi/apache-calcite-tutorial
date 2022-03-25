package com.github.linyanshi.tutorial.common.sql2rel;

import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.rel.type.RelDataTypeSystem;
import org.apache.calcite.sql.type.SqlTypeFactoryImpl;

/**
 * @author linyanshi
 * @date 2022-01-30 15:50:00
 */
public class SqlToRelBase {


    protected RelDataTypeFactory createTypeFactory() {
        return new SqlTypeFactoryImpl(RelDataTypeSystem.DEFAULT);
    }

}
