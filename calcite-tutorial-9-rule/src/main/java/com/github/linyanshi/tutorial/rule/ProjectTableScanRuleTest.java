package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * 有问题,没有看明白
 * @author linyanshi
 * @date 2022-02-19 15:53:00
 */
public class ProjectTableScanRuleTest {
    public static void main(String[] args) {
        String sql = "select * from hr.emps";
//        RuleTester.printProcessRule(sql, ProjectTableScanRule.INSTANCE);
        RuleTester.printProcessRule(sql, CoreRules.PROJECT_TABLE_SCAN);
    }
}
