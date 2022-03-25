package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * 从聚合中删除常量键。
 * @author linyanshi
 * @date 2022-01-31 19:42:00
 */
public class AggregateProjectPullUpConstantsRuleTest {
    public static void main(String[] args) {
        String sql = "select count(*) as c "
                + "from hr.emps "
                + "where deptno = 10 "
                + "group by deptno, salary";
        RuleTester.printOriginalCompare(sql,
//                AggregateProjectPullUpConstantsRule.INSTANCE);
                CoreRules.AGGREGATE_PROJECT_PULL_UP_CONSTANTS);
    }

    /**
     sql:
     select count(*) as c from hr.emps where deptno = 10 group by deptno, salary

     原始:
     LogicalProject(c=[$2])
      LogicalAggregate(group=[{0, 1}], c=[COUNT()])
       LogicalProject(deptno=[$1], salary=[$3])
        LogicalFilter(condition=[=(CAST($1):INTEGER NOT NULL, 10)])
         EnumerableTableScan(table=[[hr, emps]])


     优化后:
     LogicalProject(c=[$2])
      LogicalProject(deptno=[10], salary=[$0], c=[$1])
       LogicalAggregate(group=[{1}], c=[COUNT()])
        LogicalProject(deptno=[$1], salary=[$3])
         LogicalFilter(condition=[=(CAST($1):INTEGER NOT NULL, 10)])
          EnumerableTableScan(table=[[hr, emps]])
     */
}
