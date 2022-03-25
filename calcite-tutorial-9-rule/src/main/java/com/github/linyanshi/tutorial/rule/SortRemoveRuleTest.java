package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.SortRemoveRule;

/**
 * 目前有些问题
 * @author linyanshi
 * @date 2022-02-20 10:36:00
 */
public class SortRemoveRuleTest {
    public static void main(String[] args) {
        String sql = "select * from hr.emps order by name";
        // RuleCompare.printVolcanoPlannerProcessRule(sql, SortRemoveRule.INSTANCE);
    }
}
