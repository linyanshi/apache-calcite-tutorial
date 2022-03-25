package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * 将project和multiJoin的projFields 合并到一起
 * @author linyanshi
 * @date 2022-02-19 15:09:00
 */
public class ProjectMultiJoinMergeRuleTest {
    public static void main(String[] args) {
        String sql = "select e.name as ename,d.name as dname from hr.emps e join hr.depts d on e.deptno = d.deptno";
        // HepMatchOrder.BOTTOM_UP
//        RuleTester.printProcessRule(sql, JoinToMultiJoinRule.INSTANCE, ProjectMultiJoinMergeRule.INSTANCE);
        RuleTester.printProcessRule(sql, CoreRules.JOIN_TO_MULTI_JOIN, CoreRules.PROJECT_MULTI_JOIN_MERGE);

    }
    /**
     *
     sql:
     select e.name as ename,d.name as dname from hr.emps e join hr.depts d on e.deptno = d.deptno

     原始:
    LogicalProject(ename=[$2], dname=[$6])
      LogicalJoin(condition=[=($1, $5)], joinType=[inner])
        EnumerableTableScan(table=[[hr, emps]])
        EnumerableTableScan(table=[[hr, depts]])


    规则:JoinToMultiJoinRule
    LogicalProject(ename=[$2], dname=[$6])
      MultiJoin(joinFilter=[=($1, $5)], isFullOuterJoin=[false], joinTypes=[[INNER, INNER]], outerJoinConditions=[[NULL, NULL]], projFields=[[ALL, ALL]])
        EnumerableTableScan(table=[[hr, emps]])
        EnumerableTableScan(table=[[hr, depts]])


    规则:ProjectMultiJoinMergeRule
    LogicalProject(ename=[$2], dname=[$6])
      MultiJoin(joinFilter=[=($1, $5)], isFullOuterJoin=[false], joinTypes=[[INNER, INNER]], outerJoinConditions=[[NULL, NULL]], projFields=[[{2}, {1}]])
        EnumerableTableScan(table=[[hr, emps]])
        EnumerableTableScan(table=[[hr, depts]])
     */
}
