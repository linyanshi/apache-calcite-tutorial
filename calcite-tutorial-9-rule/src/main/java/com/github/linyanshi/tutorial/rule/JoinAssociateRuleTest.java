package com.github.linyanshi.tutorial.rule;

import org.apache.calcite.rel.rules.CoreRules;

/**
 * 它根据结合律规则更改连接
 * ((a JOIN b) JOIN c) → (a JOIN (b JOIN c))
 * @author linyanshi
 * @date 2022-02-02 15:51:00
 * TODO:
 */
public class JoinAssociateRuleTest {
    public static void main(String[] args) {
        String sql = "select e.name as ename,d3.name as dname from hr.emps e " +
                "join hr.depts d1 on e.deptno = d1.deptno " +
                "join hr.depts d2 on e.deptno = d2.deptno " +
                "join hr.depts d3 on e.deptno = d3.deptno ";
        RuleTester.printOriginalCompare(sql,
//                JoinAssociateRule.INSTANCE);
                CoreRules.JOIN_ASSOCIATE);
    }
}
