package com.github.linyanshi.calcite.parser.tutorial.sample.statement;

import com.github.linyanshi.calcite.parser.tutorial.sample.AbstractSample;
import org.apache.calcite.sql.parser.SqlParseException;

/**
 * @author linyanshi
 * @date 2022-04-25 11:31:00
 */
public class SimpleIdentifierSample extends AbstractSample {
    public static void main(String[] args) throws SqlParseException {
        System.out.println(parser.parseQuery("simple_identifier_sample aa").getClass());
    }
}
