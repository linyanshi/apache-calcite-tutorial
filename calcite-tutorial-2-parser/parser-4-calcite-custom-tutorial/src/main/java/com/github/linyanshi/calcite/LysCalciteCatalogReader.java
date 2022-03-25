package com.github.linyanshi.calcite;

import org.apache.calcite.config.CalciteConnectionConfig;
import org.apache.calcite.jdbc.CalciteSchema;
import org.apache.calcite.prepare.CalciteCatalogReader;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.sql.validate.SqlNameMatchers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author linyanshi
 * @version 2022-03-21
 */
public class LysCalciteCatalogReader extends CalciteCatalogReader {

    public LysCalciteCatalogReader(
            CalciteSchema rootSchema,
            List<List<String>> defaultSchemas,
            RelDataTypeFactory typeFactory,
            CalciteConnectionConfig config) {

        super(
                rootSchema,
                new LysSqlNameMatcher(
                        SqlNameMatchers.withCaseSensitive(config != null && config.caseSensitive()),
                        typeFactory),
                Stream.concat(defaultSchemas.stream(), Stream.of(Collections.<String>emptyList()))
                        .collect(Collectors.toList()),
                typeFactory,
                config);
    }

}
