package com.github.linyanshi.tutorial.common.parser;

import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParser;

/**
 * @author linyanshi
 * @date 2022-01-30 16:06:00
 */
public class SqlParserBase {

    public static SqlParser.Config MYSQL = SqlParser.config().withLex(Lex.MYSQL);

    public SqlNode parseQuery(String sql) throws Exception {
        SqlParser parser = SqlParser.create(sql, MYSQL);
        return parser.parseQuery();
    }

    public SqlNode parseQuery(String sql, SqlParser.Config config) throws Exception {
        SqlParser parser = SqlParser.create(sql, config);
        return parser.parseQuery();
    }

}
