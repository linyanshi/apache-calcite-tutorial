package com.github.linyanshi.calcite.parser.tutorial.sample.statement;

import com.github.linyanshi.calcite.parser.tutorial.sample.AbstractSample;
import org.apache.calcite.sql.parser.SqlParseException;

/**
 * @author linyanshi
 * @date 2022-04-25 11:25:00
 */
public class IdentifierSample extends AbstractSample {
    public static void main(String[] args) throws SqlParseException {
        System.out.println(parser.parseQuery("identifier_sample a").getClass());
    }
}
