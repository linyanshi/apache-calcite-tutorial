package com.github.linyanshi.calcite.parser.tutorial.sample.statement;

import com.github.linyanshi.calcite.parser.tutorial.sample.AbstractSample;
import org.apache.calcite.sql.parser.SqlParseException;

/**
 * @author linyanshi
 * @date 2022-04-24 15:46:00
 */
public class UnsignedNumericLiteralSample extends AbstractSample {

    public static void main(String[] args) throws SqlParseException {
        System.out.println(parser.parseQuery("unsigned_numeric_literal_sample 123").getClass());
    }

}
