package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * @author linyanshi
 * @date 2022-02-19 14:51:00
 * description 报错
 */
public class MultiJoinProjectTransposeRuleTest {
    public static void main(String[] args) {
        String sql = "select e.name from hr.emps e left join hr.depts d on e.deptno = d.deptno";

        RuleTester.printProcessRule(sql,
                // project下推大到join中
//                ProjectJoinTransposeRule.INSTANCE,
                CoreRules.PROJECT_JOIN_TRANSPOSE,
//                JoinToMultiJoinRule.INSTANCE,
                CoreRules.JOIN_TO_MULTI_JOIN,
//                MultiJoinProjectTransposeRule.MULTI_LEFT_PROJECT);
                CoreRules.MULTI_JOIN_LEFT_PROJECT);

    }
}
