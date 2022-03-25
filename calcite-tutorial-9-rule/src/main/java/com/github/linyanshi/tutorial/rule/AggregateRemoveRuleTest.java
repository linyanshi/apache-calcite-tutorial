package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * 分组字段是key时,移除掉分组字段
 * @author linyanshi
 * @date 2022-02-01 10:44:00
 */
public class AggregateRemoveRuleTest {
    public static void main(String[] args) {
        String sql = "select empid from hr.emps group by empid";
//        RuleTester.printOriginalCompare(sql, AggregateRemoveRule.INSTANCE);
        RuleTester.printOriginalCompare(sql, CoreRules.AGGREGATE_REMOVE);
    }
    /**
     sql:
     select empid from hr.emps group by empid

     原始:
     LogicalAggregate(group=[{0}])
      LogicalProject(empid=[$0])
       EnumerableTableScan(table=[[hr, emps]])


     优化后:
     LogicalProject(empid=[$0])
      EnumerableTableScan(table=[[hr, emps]])
     */
}
