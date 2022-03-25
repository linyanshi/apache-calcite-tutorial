package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * @author linyanshi
 * @date 2022-02-19 17:43:00
 */
public class SemiJoinFilterTransposeRuleTest {
    public static void main(String[] args) {
        String sql = "select e.name as ename,d.name as dname from hr.emps e join hr.depts d on e.deptno = d.deptno" +
                " where e.name = 'abc'";
        RuleTester.printProcessRule(sql,
                // filter下推到join
//                FilterJoinRule.FILTER_ON_JOIN,
                CoreRules.FILTER_INTO_JOIN,
                // 转成成semiJoin
//                JoinAddRedundantSemiJoinRule.INSTANCE,
                CoreRules.JOIN_ADD_REDUNDANT_SEMI_JOIN,
                // semiJoin下推到filter
//                SemiJoinFilterTransposeRule.INSTANCE);
                CoreRules.SEMI_JOIN_FILTER_TRANSPOSE);

    }
}
