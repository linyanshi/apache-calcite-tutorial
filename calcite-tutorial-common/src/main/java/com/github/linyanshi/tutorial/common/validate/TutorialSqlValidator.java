package com.github.linyanshi.tutorial.common.validate;

import com.github.linyanshi.tutorial.common.catalog.TutorialCalciteCatalogReader;
import com.github.linyanshi.tutorial.common.function.DedupFunction;
import org.apache.calcite.config.NullCollation;
import org.apache.calcite.jdbc.JavaTypeFactoryImpl;
import org.apache.calcite.rel.type.RelDataType;
import org.apache.calcite.rel.type.RelDataTypeFactory;
import org.apache.calcite.sql.SqlFunction;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlOperatorTable;
import org.apache.calcite.sql.fun.SqlStdOperatorTable;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.validate.SqlValidator;
import org.apache.calcite.sql.validate.SqlValidatorCatalogReader;
import org.apache.calcite.sql.validate.SqlValidatorImpl;

import java.util.List;

/**
 *         SqlValidator.Config config = SqlValidator.Config.DEFAULT
 *                 .withIdentifierExpansion(true)
 *                 .withDefaultNullCollation(NullCollation.LOW)
 *                 .withTypeCoercionEnabled(false);
 * @author linyanshi
 * @date 2022-01-31 14:26:00
 */
public class TutorialSqlValidator extends SqlValidatorImpl {


    public TutorialSqlValidator(SqlOperatorTable opTab, SqlValidatorCatalogReader catalogReader, RelDataTypeFactory typeFactory, SqlValidator.Config config) {
        super(opTab, catalogReader, typeFactory, config);
    }

    public static SqlValidatorImpl createSqlValidator(SqlValidatorCatalogReader catalogReader){
        SqlStdOperatorTable sqlStdOperatorTable = SqlStdOperatorTable.instance();
        // 注册ramp函数
        sqlStdOperatorTable.register(new DedupFunction());
//        SqlConformance config  = SqlConformanceEnum.DEFAULT;
        SqlValidator.Config config = SqlValidator.Config.DEFAULT
                                .withIdentifierExpansion(true)
                                 .withDefaultNullCollation(NullCollation.LOW)
                                 .withTypeCoercionEnabled(false);
        return new TutorialSqlValidator(sqlStdOperatorTable,catalogReader, new JavaTypeFactoryImpl(), config);
    }

    public static SqlValidatorImpl createMockSqlValidator(SqlParser.Config parserConfig){
        return createSqlValidator(TutorialCalciteCatalogReader.createMockCatalogReader(parserConfig));
    }

    @Override
    public void validateColumnListParams(
            SqlFunction function,
            List<RelDataType> argTypes,
            List<SqlNode> operands) {
        // throw new UnsupportedOperationException();
    }

    /**
     * 返回表示“未知”类型的对象
     * @return
     */
    @Override
    public RelDataType getUnknownType() {
        return super.getUnknownType();
    }
}
