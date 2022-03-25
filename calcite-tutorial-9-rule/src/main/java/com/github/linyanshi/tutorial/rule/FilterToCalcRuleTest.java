package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * Filter转换成Calc
 *
 * @author linyanshi
 * @date 2022-02-01 17:31:00
 */
public class FilterToCalcRuleTest {
    public static void main(String[] args) {
        String sql = "select * from hr.emps where name='abcd'";
        RuleTester.printOriginalCompare(sql,
//                FilterToCalcRule.INSTANCE);
                CoreRules.FILTER_TO_CALC);
    }

    /**
     *
    sql:
    select * from hr.emps where name='abcd'

    原始:
    LogicalProject(empid=[$0], deptno=[$1], name=[$2], salary=[$3], commission=[$4])
      LogicalFilter(condition=[=($2, 'abcd')])
        EnumerableTableScan(table=[[hr, emps]])


    优化后:
    LogicalProject(empid=[$0], deptno=[$1], name=[$2], salary=[$3], commission=[$4])
      LogicalCalc(expr#0..4=[{inputs}], expr#5=['abcd'], expr#6=[=($t2, $t5)], proj#0..4=[{exprs}], $condition=[$t6])
        EnumerableTableScan(table=[[hr, emps]])
     */
}
