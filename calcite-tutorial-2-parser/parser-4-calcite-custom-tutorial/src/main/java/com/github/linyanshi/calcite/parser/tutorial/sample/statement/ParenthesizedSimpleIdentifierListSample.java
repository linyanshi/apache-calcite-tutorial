package com.github.linyanshi.calcite.parser.tutorial.sample.statement;

import com.github.linyanshi.calcite.parser.tutorial.sample.AbstractSample;
import org.apache.calcite.sql.parser.SqlParseException;

/**
 * @author linyanshi
 * @date 2022-04-25 11:35:00
 */
public class ParenthesizedSimpleIdentifierListSample extends AbstractSample {
    public static void main(String[] args) throws SqlParseException {
        System.out.println(parser.parseQuery("simple_identifier_comma_list_sample (a,b,c,d)").getClass());
    }
}
