package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * Filer下推到SetOp中(union expect)
 * @author linyanshi
 * @date 2022-02-02 13:51:00
 */
public class FilterSetOpTransposeRuleTest {
    public static void main(String[] args) {

        String sql = "" +
                "select * from " +
                "(select name from hr.emps " +
                "union all " +
                "select name from hr.emps) t " +
                "where name = '1'";
        RuleTester.printOriginalCompare(sql,
//                FilterSetOpTransposeRule.INSTANCE);
                  CoreRules.FILTER_SET_OP_TRANSPOSE);
    }
    /**
     sql:
    select * from (select name from hr.emps union all select name from hr.emps) t where name = '1'

    原始:
    LogicalProject(name=[$0])
      LogicalFilter(condition=[=($0, '1')])
        LogicalUnion(all=[true])
          LogicalProject(name=[$2])
            EnumerableTableScan(table=[[hr, emps]])
          LogicalProject(name=[$2])
            EnumerableTableScan(table=[[hr, emps]])


    优化后:
    LogicalProject(name=[$0])
      LogicalUnion(all=[true])
        LogicalFilter(condition=[=($0, '1')])
          LogicalProject(name=[$2])
            EnumerableTableScan(table=[[hr, emps]])
        LogicalFilter(condition=[=($0, '1')])
          LogicalProject(name=[$2])
            EnumerableTableScan(table=[[hr, emps]])
     */
}
