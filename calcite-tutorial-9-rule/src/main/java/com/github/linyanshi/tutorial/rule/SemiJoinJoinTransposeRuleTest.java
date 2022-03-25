package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * semiJoin下推到join
 * @author linyanshi
 * @date 2022-02-19 17:47:00
 */
public class SemiJoinJoinTransposeRuleTest {
    public static void main(String[] args) {
        String sql = "select e1.name from hr.emps e1, hr.depts d, hr.emps e2 "
                + "where e1.deptno = d.deptno and e1.empid = e2.empid";
        RuleTester.printProcessRule(sql,
                // filter下推到join
//                FilterJoinRule.FILTER_ON_JOIN,
                CoreRules.FILTER_INTO_JOIN,
                // 转成semiJoin
//                JoinAddRedundantSemiJoinRule.INSTANCE,
                CoreRules.JOIN_ADD_REDUNDANT_SEMI_JOIN,
                // semiJoin下推到join
//                SemiJoinJoinTransposeRule.INSTANCE);
                CoreRules.SEMI_JOIN_JOIN_TRANSPOSE);

    }

    /**
    sql:
    select e1.name from hr.emps e1, hr.depts d, hr.emps e2 where e1.deptno = d.deptno and e1.empid = e2.empid

    原始:
    LogicalProject(name=[$2])
      LogicalFilter(condition=[AND(=($1, $5), =($0, $8))])
        LogicalJoin(condition=[true], joinType=[inner])
          LogicalJoin(condition=[true], joinType=[inner])
            EnumerableTableScan(table=[[hr, emps]])
            EnumerableTableScan(table=[[hr, depts]])
          EnumerableTableScan(table=[[hr, emps]])


    规则:FilterJoinRule:FilterJoinRule:filter
    LogicalProject(name=[$2])
      LogicalJoin(condition=[=($0, $8)], joinType=[inner])
        LogicalJoin(condition=[=($1, $5)], joinType=[inner])
          EnumerableTableScan(table=[[hr, emps]])
          EnumerableTableScan(table=[[hr, depts]])
        EnumerableTableScan(table=[[hr, emps]])


    规则:JoinAddRedundantSemiJoinRule
    LogicalProject(name=[$2])
      LogicalJoin(condition=[=($0, $8)], joinType=[inner], semiJoinDone=[true])
        SemiJoin(condition=[=($0, $8)], joinType=[inner])
          LogicalJoin(condition=[=($1, $5)], joinType=[inner], semiJoinDone=[true])
            SemiJoin(condition=[=($1, $5)], joinType=[inner])
              EnumerableTableScan(table=[[hr, emps]])
              EnumerableTableScan(table=[[hr, depts]])
            EnumerableTableScan(table=[[hr, depts]])
          EnumerableTableScan(table=[[hr, emps]])
        EnumerableTableScan(table=[[hr, emps]])


    规则:SemiJoinJoinTransposeRule
    LogicalProject(name=[$2])
      LogicalJoin(condition=[=($0, $8)], joinType=[inner], semiJoinDone=[true])
        LogicalJoin(condition=[=($1, $5)], joinType=[inner], semiJoinDone=[true])
          SemiJoin(condition=[=($0, $5)], joinType=[inner])
            SemiJoin(condition=[=($1, $5)], joinType=[inner])
              EnumerableTableScan(table=[[hr, emps]])
              EnumerableTableScan(table=[[hr, depts]])
            EnumerableTableScan(table=[[hr, emps]])
          EnumerableTableScan(table=[[hr, depts]])
        EnumerableTableScan(table=[[hr, emps]])
     */
}
