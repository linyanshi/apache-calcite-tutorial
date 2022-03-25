package com.github.linyanshi.calcite.parser.tutorial.sample.statement;

import com.github.linyanshi.calcite.parser.tutorial.sample.AbstractSample;
import org.apache.calcite.sql.parser.SqlParseException;

/**
 * @author linyanshi
 * @date 2022-04-24 17:47:00
 */
public class NumericLiteralSample extends AbstractSample {

    public static void main(String[] args) throws SqlParseException {
        System.out.println(parser.parseQuery("numeric_literal_sample 123").getClass());
        System.out.println(parser.parseQuery("numeric_literal_sample -123").getClass());
        System.out.println(parser.parseQuery("numeric_literal_sample +123").getClass());
    }

}
