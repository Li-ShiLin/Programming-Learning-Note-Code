package com.action.drools;

import com.action.drools.entity.ComparisonOperatorEntity;
import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code comparisonOperator.drl}：比较操作符与执行指定规则。
 */
public class ComparisonOperatorTest {

    private static ComparisonOperatorEntity sampleEntity() {
        ComparisonOperatorEntity entity = new ComparisonOperatorEntity();
        entity.setNames("张三");
        List<String> list = new ArrayList<String>();
        list.add("张三");
        list.add("李四");
        entity.setList(list);
        return entity;
    }

    @Test
    public void testComparisonOperator() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();

        kieSession.insert(sampleEntity());
        kieSession.fireAllRules();
        kieSession.dispose();
        assertTrue(true);
    }

    @Test
    public void testComparisonOperatorFilter() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();

        kieSession.insert(sampleEntity());
        kieSession.fireAllRules(AgendaFilters.filterByRuleName("rule_comparison_memberOf"));
        kieSession.dispose();
        assertTrue(true);
    }

    @Test
    public void testComparisonOperatorFilterWithRuleNameEquals() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();

        kieSession.insert(sampleEntity());
        kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("rule_comparison_memberOf"));
        kieSession.dispose();
        assertTrue(true);
    }
}
