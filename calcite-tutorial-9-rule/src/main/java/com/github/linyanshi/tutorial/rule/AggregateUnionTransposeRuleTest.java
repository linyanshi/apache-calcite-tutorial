package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * 将聚合(Aggregate)下推到union中
 *
 * @author linyanshi
 * @date 2022-02-01 14:15:00
 */
public class AggregateUnionTransposeRuleTest {
    public static void main(String[] args) {
        String sql = "select name, sum(u) from " +
                "(select *, 2 u from hr.emps as e1 " +
                "union all " +
                "select *, 3 u from hr.emps as e2) " +
                "group by name";
        RuleTester.printProcessRule(sql,
                // 将project(投影) 下推到 SetOp(例如:union ,minus, except)
//                ProjectSetOpTransposeRule.INSTANCE,
                CoreRules.PROJECT_SET_OP_TRANSPOSE,
                // 合并投影
//                ProjectMergeRulee.INSTANCE,
                CoreRules.PROJECT_MERGE,
//                AggregateUnionTransposeRule.INSTANCE
                CoreRules.AGGREGATE_UNION_TRANSPOSE
        );
    }
    /**
        sql:
        select name, sum(u) from (select *, 2 u from hr.emps as e1 union all select *, 3 u from hr.emps as e2) group by name

        原始:
        LogicalAggregate(group=[{0}], EXPR$1=[SUM($1)])
          LogicalProject(name=[$2], u=[$5])
            LogicalUnion(all=[true])
              LogicalProject(empid=[$0], deptno=[$1], name=[$2], salary=[$3], commission=[$4], u=[2])
                EnumerableTableScan(table=[[hr, emps]])
              LogicalProject(empid=[$0], deptno=[$1], name=[$2], salary=[$3], commission=[$4], u=[3])
                EnumerableTableScan(table=[[hr, emps]])


        规则:ProjectSetOpTransposeRule  将project(投影) 下推到 SetOp(例如:union ,minus, except)
        LogicalAggregate(group=[{0}], EXPR$1=[SUM($1)])
          LogicalUnion(all=[true])
            LogicalProject(name=[$2], u=[$5])
              LogicalProject(empid=[$0], deptno=[$1], name=[$2], salary=[$3], commission=[$4], u=[2])
                EnumerableTableScan(table=[[hr, emps]])
            LogicalProject(name=[$2], u=[$5])
              LogicalProject(empid=[$0], deptno=[$1], name=[$2], salary=[$3], commission=[$4], u=[3])
                EnumerableTableScan(table=[[hr, emps]])


        规则:ProjectMergeRule:force_mode  合并投影
        LogicalAggregate(group=[{0}], EXPR$1=[SUM($1)])
          LogicalUnion(all=[true])
            LogicalProject(name=[$2], u=[2])
              EnumerableTableScan(table=[[hr, emps]])
            LogicalProject(name=[$2], u=[3])
              EnumerableTableScan(table=[[hr, emps]])


        规则:AggregateUnionTransposeRule
        LogicalAggregate(group=[{0}], EXPR$1=[SUM($1)])
          LogicalUnion(all=[true])
            LogicalAggregate(group=[{0}], EXPR$1=[SUM($1)])
              LogicalProject(name=[$2], u=[2])
                EnumerableTableScan(table=[[hr, emps]])
            LogicalAggregate(group=[{0}], EXPR$1=[SUM($1)])
              LogicalProject(name=[$2], u=[3])
                EnumerableTableScan(table=[[hr, emps]])
     */
}
