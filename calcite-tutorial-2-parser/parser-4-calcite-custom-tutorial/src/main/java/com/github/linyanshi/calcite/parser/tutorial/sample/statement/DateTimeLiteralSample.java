package com.github.linyanshi.calcite.parser.tutorial.sample.statement;

import com.github.linyanshi.calcite.parser.tutorial.sample.AbstractSample;
import org.apache.calcite.sql.parser.SqlParseException;

/**
 * @author linyanshi
 * @date 2022-04-25 11:16:00
 */
public class DateTimeLiteralSample extends AbstractSample {
    public static void main(String[] args) throws SqlParseException {
        System.out.println(parser.parseQuery("date_time_literal_sample DATE '2004-10-22'").getClass());
    }
}
