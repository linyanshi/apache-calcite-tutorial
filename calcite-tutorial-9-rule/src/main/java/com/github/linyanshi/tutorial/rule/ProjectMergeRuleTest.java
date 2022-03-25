package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * @author linyanshi
 * @date 2022-02-01 15:31:00
 * 将投影(project)和投影(project)合并
 */
public class ProjectMergeRuleTest {
    public static void main(String[] args) {
        String sql = "select salary from " +
                "(select * from hr.emps e1 " +
                "union all " +
                "select * from hr.emps e2) ";
        RuleTester.printProcessRule(sql,
                // 将project(投影) 下推到 SetOp(例如:union ,minus, except)
//                ProjectSetOpTransposeRule.INSTANCE,
//                ProjectMergeRule.INSTANCE
                CoreRules.PROJECT_SET_OP_TRANSPOSE,
                CoreRules.PROJECT_MERGE
        );
    }

    /**
    sql:
    select salary from (select * from hr.emps e1 union all select * from hr.emps e2)

    原始:
    LogicalProject(salary=[$3])
      LogicalUnion(all=[true])
        LogicalProject(empid=[$0], deptno=[$1], name=[$2], salary=[$3], commission=[$4])
          EnumerableTableScan(table=[[hr, emps]])
        LogicalProject(empid=[$0], deptno=[$1], name=[$2], salary=[$3], commission=[$4])
          EnumerableTableScan(table=[[hr, emps]])


    规则:ProjectSetOpTransposeRule
    LogicalUnion(all=[true])
      LogicalProject(salary=[$3])
        LogicalProject(empid=[$0], deptno=[$1], name=[$2], salary=[$3], commission=[$4])
          EnumerableTableScan(table=[[hr, emps]])
      LogicalProject(salary=[$3])
        LogicalProject(empid=[$0], deptno=[$1], name=[$2], salary=[$3], commission=[$4])
          EnumerableTableScan(table=[[hr, emps]])


    规则:ProjectMergeRule:force_mode
    LogicalUnion(all=[true])
      LogicalProject(salary=[$3])
        EnumerableTableScan(table=[[hr, emps]])
      LogicalProject(salary=[$3])
        EnumerableTableScan(table=[[hr, emps]])

     */
}
