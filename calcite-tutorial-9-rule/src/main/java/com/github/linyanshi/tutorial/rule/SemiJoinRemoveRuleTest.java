package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * 移除SemiJoin
 * @author linyanshi
 * @date 2022-02-20 10:12:00
 */
public class SemiJoinRemoveRuleTest {
    public static void main(String[] args) {
        String sql = "select e.name as ename,d.name as dname from hr.emps e join hr.depts d on e.deptno = d.deptno";
        RuleTester.printProcessRule(sql,
//                JoinAddRedundantSemiJoinRule.INSTANCE,
                CoreRules.JOIN_ADD_REDUNDANT_SEMI_JOIN,
//                SemiJoinRemoveRule.INSTANCE
                CoreRules.SEMI_JOIN_REMOVE
        );
    }
    /**
     sql:
    select e.name as ename,d.name as dname from hr.emps e join hr.depts d on e.deptno = d.deptno

    原始:
    LogicalProject(ename=[$2], dname=[$6])
      LogicalJoin(condition=[=($1, $5)], joinType=[inner])
        EnumerableTableScan(table=[[hr, emps]])
        EnumerableTableScan(table=[[hr, depts]])


    规则:JoinAddRedundantSemiJoinRule
    LogicalProject(ename=[$2], dname=[$6])
      LogicalJoin(condition=[=($1, $5)], joinType=[inner], semiJoinDone=[true])
        SemiJoin(condition=[=($1, $5)], joinType=[inner])
          EnumerableTableScan(table=[[hr, emps]])
          EnumerableTableScan(table=[[hr, depts]])
        EnumerableTableScan(table=[[hr, depts]])


    规则:SemiJoinRemoveRule
    LogicalProject(ename=[$2], dname=[$6])
      LogicalJoin(condition=[=($1, $5)], joinType=[inner], semiJoinDone=[true])
        EnumerableTableScan(table=[[hr, emps]])
        EnumerableTableScan(table=[[hr, depts]])
     */
}
