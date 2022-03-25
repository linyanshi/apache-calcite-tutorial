package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * 转换 semi-join
 * LogicalJoin(X, Y) → LogicalJoin(SemiJoin(X, Y), Y)
 * @author linyanshi
 * @date 2022-02-02 15:48:00
 */
public class JoinAddRedundantSemiJoinRuleTest {
    public static void main(String[] args) {
        String sql = "select e.name as ename,d.name as dname from hr.emps e join hr.depts d on e.deptno = d.deptno";
        RuleTester.printOriginalCompare(sql,
//                JoinAddRedundantSemiJoinRule.INSTANCE);
                CoreRules.JOIN_ADD_REDUNDANT_SEMI_JOIN);
    }
    /**
     sql:
    select e.name as ename,d.name as dname from hr.emps e join hr.depts d on e.deptno = d.deptno

    原始:
    LogicalProject(ename=[$2], dname=[$6])
      LogicalJoin(condition=[=($1, $5)], joinType=[inner])
        EnumerableTableScan(table=[[hr, emps]])
        EnumerableTableScan(table=[[hr, depts]])


    优化后:
    LogicalProject(ename=[$2], dname=[$6])
      LogicalJoin(condition=[=($1, $5)], joinType=[inner], semiJoinDone=[true])
        SemiJoin(condition=[=($1, $5)], joinType=[inner])
          EnumerableTableScan(table=[[hr, emps]])
          EnumerableTableScan(table=[[hr, depts]])
        EnumerableTableScan(table=[[hr, depts]])
     */
}
