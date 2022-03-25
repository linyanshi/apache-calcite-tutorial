package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * 删除多余的project
 * @author linyanshi
 * @date 2022-02-19 15:38:00
 */
public class ProjectRemoveRuleTest {
    public static void main(String[] args) {
        String sql = "select salary from " +
                "(select * from hr.emps e1 " +
                "union all " +
                "select * from hr.emps e2) ";
        RuleTester.printProcessRule(sql,
                // 将project(投影) 下推到 SetOp(例如:union ,minus, except)
//                ProjectSetOpTransposeRule.INSTANCE,
                CoreRules.PROJECT_SET_OP_TRANSPOSE,
//                ProjectRemoveRule.INSTANCE);
                CoreRules.PROJECT_REMOVE);
    }
}
