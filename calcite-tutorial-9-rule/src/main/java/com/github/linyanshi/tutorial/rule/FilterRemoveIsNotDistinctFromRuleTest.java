package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * 移除filter中的is not distinct from ,目前没有办法测试calcite解析sql的时候回解析成or的格式
 * https://dev.mysql.com/doc/refman/8.0/en/comparison-operators.html
 * @author linyanshi
 * @date 2022-02-02 12:05:00
 */
public class FilterRemoveIsNotDistinctFromRuleTest {
    public static void main(String[] args) {
        String sql = "select * from hr.emps where deptno is not distinct from 20";
        RuleTester.printProcessRule(sql,
//                FilterRemoveIsNotDistinctFromRule.INSTANCE);
        CoreRules.FILTER_EXPAND_IS_NOT_DISTINCT_FROM);
    }
}
