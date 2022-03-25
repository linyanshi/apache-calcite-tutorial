package com.github.linyanshi.delegation;

import com.github.linyanshi.api.Transformation;
import com.github.linyanshi.catalog.CatalogManager;
import com.github.linyanshi.catalog.CatalogManagerCalciteSchema;
import com.github.linyanshi.opertions.Operation;

import java.util.List;

import org.apache.calcite.jdbc.CalciteSchemaBuilder;

/**
 *
 * @author linyanshi
 * @version 2022-03-21
 */
public class LysPlanner implements Planner {

    private final Parser parser;

    public LysPlanner(Executor executor, CatalogManager catalogManager) {
        // 创建解析器
        this.parser = LysParser.create(CalciteSchemaBuilder.asRootSchema(new CatalogManagerCalciteSchema(catalogManager)),
                catalogManager);
    }

    @Override
    public Parser getParser() {
        return this.parser;
    }

    @Override
    public List<Transformation<?>> translate(List<Operation> operations) {
        System.out.println("operation转换成transformation");
        return null;
    }
}
