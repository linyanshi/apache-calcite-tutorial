package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * sort移动到union下
 * @author linyanshi
 * @date 2022-02-20 11:12:00
 */
public class SortUnionTransposeRuleTest {
    public static void main(String[] args) {
        final String sql = "select a.name,a.deptno from hr.depts a\n"
                + "union all\n"
                + "select b.name,b.deptno from hr.depts b\n"
                + "order by name";
        RuleTester.printProcessRule(sql,
//                ProjectSetOpTransposeRule.INSTANCE,
                CoreRules.PROJECT_SET_OP_TRANSPOSE,
//                SortUnionTransposeRule.MATCH_NULL_FETCH);
                CoreRules.SORT_UNION_TRANSPOSE_MATCH_NULL_FETCH);

    }
    /**
     sql:
    select a.name,a.deptno from hr.depts a
    union all
    select b.name,b.deptno from hr.depts b
    order by name

    原始:
    LogicalSort(sort0=[$0], dir0=[ASC])
      LogicalProject(name=[$0], deptno=[$1])
        LogicalUnion(all=[true])
          LogicalProject(name=[$1], deptno=[$0])
            EnumerableTableScan(table=[[hr, depts]])
          LogicalProject(name=[$1], deptno=[$0])
            EnumerableTableScan(table=[[hr, depts]])


    规则:ProjectSetOpTransposeRule
    LogicalSort(sort0=[$0], dir0=[ASC])
      LogicalUnion(all=[true])
        LogicalProject(name=[$1], deptno=[$0])
          EnumerableTableScan(table=[[hr, depts]])
        LogicalProject(name=[$1], deptno=[$0])
          EnumerableTableScan(table=[[hr, depts]])


    规则:SortUnionTransposeRule:default
    LogicalSort(sort0=[$0], dir0=[ASC])
      LogicalUnion(all=[true])
        LogicalSort(sort0=[$0], dir0=[ASC])
          LogicalProject(name=[$1], deptno=[$0])
            EnumerableTableScan(table=[[hr, depts]])
        LogicalSort(sort0=[$0], dir0=[ASC])
          LogicalProject(name=[$1], deptno=[$0])
            EnumerableTableScan(table=[[hr, depts]])
     */
}
