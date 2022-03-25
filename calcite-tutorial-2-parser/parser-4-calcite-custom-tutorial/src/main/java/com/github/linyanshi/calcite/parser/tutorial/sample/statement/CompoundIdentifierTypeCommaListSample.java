package com.github.linyanshi.calcite.parser.tutorial.sample.statement;

import com.github.linyanshi.calcite.parser.tutorial.sample.AbstractSample;
import org.apache.calcite.sql.parser.SqlParseException;

/**
 * @author linyanshi
 * @date 2022-04-25 14:07:00
 */
public class CompoundIdentifierTypeCommaListSample extends AbstractSample {
    public static void main(String[] args) throws SqlParseException {
        System.out.println(parser.parseQuery("compound_identifier_type_comma_list_sample aa,bb").getClass());
    }
}
