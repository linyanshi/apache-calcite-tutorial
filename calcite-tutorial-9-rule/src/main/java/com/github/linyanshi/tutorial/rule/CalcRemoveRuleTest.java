package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * @author linyanshi
 * @date 2022-02-01 17:56:00
 *
 * 删除Calc
 * 只有在没有过滤条件,并且入字段的原始顺序进行投影,才可以删除
 */
public class CalcRemoveRuleTest {
    public static void main(String[] args) {
        String sql = "select * from hr.emps";
        RuleTester.printProcessRule(sql,
//                ProjectToCalcRule.INSTANCE,
                CoreRules.PROJECT_TO_CALC,
//                CalcRemoveRule.INSTANCE
                CoreRules.PROJECT_REMOVE
        );
    }
    /**
    sql:
    select * from hr.emps

    原始:
    LogicalProject(empid=[$0], deptno=[$1], name=[$2], salary=[$3], commission=[$4])
      EnumerableTableScan(table=[[hr, emps]])


    规则:ProjectToCalcRule
    LogicalCalc(expr#0..4=[{inputs}], proj#0..4=[{exprs}])
      EnumerableTableScan(table=[[hr, emps]])


    规则:CalcRemoveRule
    EnumerableTableScan(table=[[hr, emps]])

     */
}
