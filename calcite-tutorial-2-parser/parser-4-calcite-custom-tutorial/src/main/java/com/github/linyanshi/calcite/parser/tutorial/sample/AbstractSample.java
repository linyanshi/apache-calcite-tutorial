package com.github.linyanshi.calcite.parser.tutorial.sample;

import com.github.linyanshi.calcite.parser.tutorial.CustomSqlParserImpl;
import org.apache.calcite.config.Lex;
import org.apache.calcite.sql.parser.SqlParser;

/**
 * @author linyanshi
 * @date 2022-04-24 15:47:00
 */
public abstract class AbstractSample {
    public static SqlParser.Config mysqlConfig = SqlParser.config().withLex(Lex.MYSQL).
            withParserFactory(CustomSqlParserImpl.FACTORY);
    public static SqlParser parser = SqlParser.create("", mysqlConfig);

}
