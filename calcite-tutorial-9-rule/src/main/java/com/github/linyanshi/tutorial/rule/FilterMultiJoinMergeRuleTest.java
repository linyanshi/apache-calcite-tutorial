package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * 将filter和MultiJoin 合并到一起
 * @author linyanshi
 * @date 2022-02-02 11:34:00
 */
public class FilterMultiJoinMergeRuleTest {
    public static void main(String[] args) {
        String sql = "select e.name as ename,d.name as dname from hr.emps e join hr.depts d on e.deptno = d.deptno where e.name = '1'";
        RuleTester.printProcessRule(sql,
//                JoinToMultiJoinRule.INSTANCE,
//                FilterMultiJoinMergeRule.INSTANCE
                CoreRules.JOIN_TO_MULTI_JOIN,
                CoreRules.FILTER_MULTI_JOIN_MERGE
        );
    }

    /**
     sql:
    select e.name as ename,d.name as dname from hr.emps e join hr.depts d on e.deptno = d.deptno where e.name = '1'

    原始:
    LogicalProject(ename=[$2], dname=[$6])
      LogicalFilter(condition=[=($2, '1')])
        LogicalJoin(condition=[=($1, $5)], joinType=[inner])
          EnumerableTableScan(table=[[hr, emps]])
          EnumerableTableScan(table=[[hr, depts]])


    规则:JoinToMultiJoinRule
    LogicalProject(ename=[$2], dname=[$6])
      LogicalFilter(condition=[=($2, '1')])
        MultiJoin(joinFilter=[=($1, $5)], isFullOuterJoin=[false], joinTypes=[[INNER, INNER]], outerJoinConditions=[[NULL, NULL]], projFields=[[ALL, ALL]])
          EnumerableTableScan(table=[[hr, emps]])
          EnumerableTableScan(table=[[hr, depts]])


    规则:FilterMultiJoinMergeRule
    LogicalProject(ename=[$2], dname=[$6])
      MultiJoin(joinFilter=[=($1, $5)], isFullOuterJoin=[false], joinTypes=[[INNER, INNER]], outerJoinConditions=[[NULL, NULL]], projFields=[[ALL, ALL]], postJoinFilter=[=($2, '1')])
        EnumerableTableScan(table=[[hr, emps]])
        EnumerableTableScan(table=[[hr, depts]])
     */
}
