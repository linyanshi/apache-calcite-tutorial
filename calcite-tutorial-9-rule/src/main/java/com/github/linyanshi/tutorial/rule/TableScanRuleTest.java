package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.TableScanRule;

/**
 * @author linyanshi
 * @date 2022-02-20 11:41:00
 * 没有测试出
 */
public class TableScanRuleTest {
    public static void main(String[] args) {
        String sql = "select name as ename from hr.emps where name='abcd'";
        RuleTester.printProcessRule(sql, TableScanRule.INSTANCE);

    }
}
