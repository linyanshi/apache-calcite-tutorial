package com.github.linyanshi.tutorial.common.sql2rel;

import org.apache.calcite.sql2rel.SqlToRelConverter;

/**
 * @author linyanshi
 * @date 2022-01-31 15:06:00
 */
public class SqlToRelConverterBase {
    public static SqlToRelConverter.Config DEFAULT = SqlToRelConverter.config()
            // 不转换相关子查询
            .withExpand(false);
//            SqlToRelConverter.configBuilder()
            // 不转换相关子查询
//            .withExpand(false)
//            .build();
}
