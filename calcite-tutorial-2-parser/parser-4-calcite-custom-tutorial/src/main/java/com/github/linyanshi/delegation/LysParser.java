package com.github.linyanshi.delegation;

import com.github.linyanshi.calcite.LysCalciteCatalogReader;
import com.github.linyanshi.calcite.LysSqlValidatorImpl;
import com.github.linyanshi.calcite.parser.tutorial.CustomSqlParserImpl;
import com.github.linyanshi.catalog.CatalogManager;
import com.github.linyanshi.opertions.Operation;
import com.github.linyanshi.opertions.ShowDatabasesOperation;
import com.github.linyanshi.calcite.parser.tutorial.SqlShowDatabases;
import org.apache.calcite.config.CalciteConnectionConfig;
import org.apache.calcite.config.CalciteConnectionConfigImpl;
import org.apache.calcite.config.CalciteConnectionProperty;
import org.apache.calcite.config.Lex;
import org.apache.calcite.config.NullCollation;
import org.apache.calcite.jdbc.CalciteSchema;
import org.apache.calcite.jdbc.JavaTypeFactoryImpl;
import org.apache.calcite.plan.RelOptPlanner;
import org.apache.calcite.plan.volcano.VolcanoPlanner;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.SqlKind;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.validate.SqlConformance;
import org.apache.calcite.sql.validate.SqlConformanceEnum;
import org.apache.calcite.sql.validate.SqlValidator;
import org.apache.calcite.sql.validate.SqlValidatorImpl;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * @author linyanshi
 * @version 2022-03-21
 */
public class LysParser implements Parser {

    private final RelDataTypeFactory typeFactory = new JavaTypeFactoryImpl();
    private final CalciteSchema rootSchema;
    private final FrameworkConfig frameworkConfig;

    private final CatalogManager catalogManager;

    public LysParser(CalciteSchema rootSchema, CatalogManager catalogManager) {
        this.rootSchema = rootSchema;
        this.catalogManager = catalogManager;

        this.frameworkConfig = createFrameworkConfig();
        RelOptPlanner planner =
                new VolcanoPlanner(frameworkConfig.getCostFactory(), frameworkConfig.getContext());
        planner.setExecutor(frameworkConfig.getExecutor());
    }

    /**
     * 创建Calcite SQL解析器
     */
    public CalciteParser createCalciteParser() {
        return new CalciteParser(getSqlParserConfig());
    }


    @Override
    public List<Operation> parse(String statement) {
        // 解析sql语句
        CalciteParser parser = createCalciteParser();
        SqlNode parsed = parser.parse(statement);

        // 对sql语句进行验证
        FrameworkConfig config = createFrameworkConfig();
        SqlValidatorImpl validator = new LysSqlValidatorImpl(config.getOperatorTable(),
                createCatalogReader(false,
                        catalogManager.getCurrentCatalog(), catalogManager.getCurrentDatabase()),
                typeFactory,
                SqlValidator.Config.DEFAULT
                        .withIdentifierExpansion(true)
                        .withDefaultNullCollation(NullCollation.LOW)
                        .withTypeCoercionEnabled(false)
        );

        SqlNode validated;
        if (parsed instanceof SqlShowDatabases) {
            validated = parsed;
        } else {
            validated = validator.validate(parsed);
        }


        Operation operation;
        if (validated instanceof SqlShowDatabases) {
            operation = convertShowDatabases((SqlShowDatabases) validated);
        } else if (validated.getKind().belongsTo(SqlKind.QUERY)) {
            operation = convertSqlQuery(validated);
        } else {
            throw new IllegalStateException("Unsupported query: " + statement);
        }

        return Collections.singletonList(operation);
    }

    private Operation convertShowDatabases(SqlShowDatabases sqlShowDatabases) {
        return new ShowDatabasesOperation();
    }

    /**
     * 查询
     */
    private Operation convertSqlQuery(SqlNode validated) {
        //TODO 未实现转换
        return null;
    }

    public static LysParser create(CalciteSchema rootSchema, CatalogManager catalogManager) {
        return new LysParser(rootSchema, catalogManager);
    }


    /**
     * CatalogReader
     */
    private LysCalciteCatalogReader createCatalogReader(
            boolean lenientCaseSensitivity, String currentCatalog, String currentDatabase) {
        SqlParser.Config sqlParserConfig = getSqlParserConfig();

        final boolean caseSensitive;
        if (lenientCaseSensitivity) {
            caseSensitive = false;
        } else {
            caseSensitive = sqlParserConfig.caseSensitive();
        }

        SqlParser.Config newSqlParserConfig =sqlParserConfig.withCaseSensitive(caseSensitive);

        SchemaPlus rootSchema = getRootSchema(this.rootSchema.plus());
        return new LysCalciteCatalogReader(
                CalciteSchema.from(rootSchema),
                asList(asList(currentCatalog, currentDatabase), singletonList(currentCatalog)),
                typeFactory,
                connectionConfig(newSqlParserConfig));
    }

    /**
     * 创建Calcite的FrameworkConfig
     */
    private FrameworkConfig createFrameworkConfig() {
        return Frameworks.newConfigBuilder()
                .defaultSchema(rootSchema.plus())
                .parserConfig(getSqlParserConfig())
                .build();
    }

    private SqlParser.Config getSqlParserConfig() {
        SqlConformance conformance = SqlConformanceEnum.DEFAULT;
        return SqlParser.config()
                .withParserFactory(CustomSqlParserImpl.FACTORY)
                .withConformance(conformance)
                .withLex(Lex.JAVA)
                .withIdentifierMaxLength(256);
    }

    private SchemaPlus getRootSchema(SchemaPlus schema) {
        if (schema.getParentSchema() == null) {
            return schema;
        } else {
            return getRootSchema(schema.getParentSchema());
        }
    }

    private CalciteConnectionConfig connectionConfig(SqlParser.Config parserConfig) {
        Properties prop = new Properties();
        prop.setProperty(CalciteConnectionProperty.CASE_SENSITIVE.camelName(),
                String.valueOf(parserConfig.caseSensitive()));
        return new CalciteConnectionConfigImpl(prop);
    }

}
