package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * sort下推到join中
 * @author linyanshi
 * @date 2022-02-20 10:22:00
 */
public class SortJoinTransposeRuleTest {
    public static void main(String[] args) {
        String sql =  "select d.* from hr.depts d left join ("
                + "  select * from hr.emps"
                + "    ) using (deptno) order by d.name";
        RuleTester.printProcessRule(sql,
//                SortProjectTransposeRule.INSTANCE,
                CoreRules.SORT_PROJECT_TRANSPOSE,
//                SortJoinTransposeRule.INSTANCE);
                CoreRules.SORT_JOIN_TRANSPOSE);

    }
    /**
    sql:
    select d.* from hr.depts d left join (  select * from hr.emps    ) using (deptno) order by d.name

    原始:
    LogicalSort(sort0=[$1], dir0=[ASC])
      LogicalProject(deptno=[$0], name=[$1], create_time=[$2])
        LogicalJoin(condition=[=($0, $4)], joinType=[left])
          EnumerableTableScan(table=[[hr, depts]])
          LogicalProject(empid=[$0], deptno=[$1], name=[$2], salary=[$3], commission=[$4])
            EnumerableTableScan(table=[[hr, emps]])


    规则:SortProjectTransposeRule
    LogicalProject(deptno=[$0], name=[$1], create_time=[$2])
      LogicalSort(sort0=[$1], dir0=[ASC])
        LogicalJoin(condition=[=($0, $4)], joinType=[left])
          EnumerableTableScan(table=[[hr, depts]])
          LogicalProject(empid=[$0], deptno=[$1], name=[$2], salary=[$3], commission=[$4])
            EnumerableTableScan(table=[[hr, emps]])


    规则:SortJoinTransposeRule
    LogicalProject(deptno=[$0], name=[$1], create_time=[$2])
      LogicalSort(sort0=[$1], dir0=[ASC])
        LogicalJoin(condition=[=($0, $4)], joinType=[left])
          LogicalSort(sort0=[$1], dir0=[ASC])
            EnumerableTableScan(table=[[hr, depts]])
          LogicalProject(empid=[$0], deptno=[$1], name=[$2], salary=[$3], commission=[$4])
            EnumerableTableScan(table=[[hr, emps]])



    Process finished with exit code 0

     */
}
