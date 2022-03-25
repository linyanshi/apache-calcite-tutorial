package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 *
 * 展开 distinct
 * @author linyanshi
 * @date 2022-01-31 16:16:00
 */
public class AggregateExpandDistinctAggregatesRuleTest {
    public static void main(String[] args) {
        String sql = "select deptno, count(distinct name) from hr.emps group by deptno";

//        RuleTester.printOriginalCompare(sql, AggregateExpandDistinctAggregatesRule.INSTANCE);
        RuleTester.printOriginalCompare(sql,
                CoreRules.AGGREGATE_EXPAND_DISTINCT_AGGREGATES,
                CoreRules.AGGREGATE_EXPAND_DISTINCT_AGGREGATES_TO_JOIN);
    }
    /**CoreRules.AGGREGATE_EXPAND_DISTINCT_AGGREGATES
     *
     注意 count(distinct name)
     sql:
     select deptno, count(distinct name) from hr.emps group by deptno

     原始:
     LogicalAggregate(group=[{0}], EXPR$1=[COUNT(DISTINCT $1)])
      LogicalProject(deptno=[$1], name=[$2])
       EnumerableTableScan(table=[[hr, emps]])


     优化后:
     LogicalAggregate(group=[{0}], EXPR$1=[COUNT($1)])
      LogicalAggregate(group=[{0, 1}])
       LogicalProject(deptno=[$1], name=[$2])
        EnumerableTableScan(table=[[hr, emps]])
     */
}
