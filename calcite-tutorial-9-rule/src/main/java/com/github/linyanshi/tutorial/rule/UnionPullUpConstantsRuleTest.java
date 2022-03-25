package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * 提取union常量值
 * @author linyanshi
 * @date 2022-02-20 11:52:00
 */
public class UnionPullUpConstantsRuleTest {
    public static void main(String[] args) {
        final String sql = "select 2, deptno from hr.emps as e1\n"
                + "union all\n"
                + "select 2, deptno from hr.emps as e2";
//        RuleTester.printProcessRule(sql, UnionPullUpConstantsRule.INSTANCE);
        RuleTester.printProcessRule(sql, CoreRules.UNION_PULL_UP_CONSTANTS);
    }
    /**
     sql:
    select 2, deptno from hr.emps as e1
    union all
    select 2, deptno from hr.emps as e2

    原始:
    LogicalUnion(all=[true])
      LogicalProject(EXPR$0=[2], deptno=[$1])
        EnumerableTableScan(table=[[hr, emps]])
      LogicalProject(EXPR$0=[2], deptno=[$1])
        EnumerableTableScan(table=[[hr, emps]])


    规则:UnionPullUpConstantsRule
    LogicalProject(EXPR$0=[2], deptno=[$0])
      LogicalUnion(all=[true])
        LogicalProject(deptno=[$1])
          LogicalProject(EXPR$0=[2], deptno=[$1])
            EnumerableTableScan(table=[[hr, emps]])
        LogicalProject(deptno=[$1])
          LogicalProject(EXPR$0=[2], deptno=[$1])
            EnumerableTableScan(table=[[hr, emps]])

     */
}
