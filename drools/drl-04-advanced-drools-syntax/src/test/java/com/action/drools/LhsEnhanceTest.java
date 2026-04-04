package com.action.drools;

import com.action.drools.entity.Student;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code lhsEnhance.drl}：LHS 加强（in/not in、not、exists、规则继承）。
 */
public class LhsEnhanceTest {

    @Test
    public void testLhsInNotIn() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Student s1 = new Student();
        s1.setName("张三");
        s1.setAge(15);
        Student s2 = new Student();
        s2.setName("赵六");
        s2.setAge(18);
        kieSession.insert(s1);
        kieSession.insert(s2);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_lhs_in"));
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_lhs_notIn"));
        kieSession.dispose();
        assertTrue(true);
    }

    @Test
    public void testLhsNot() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_lhs_not"));
        kieSession.dispose();
        assertTrue(true);
    }

    @Test
    public void testLhsExists() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        kieSession.insert(new Student());
        kieSession.insert(new Student());
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_lhs_exists"));
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_lhs_noExists"));
        kieSession.dispose();
        assertTrue(true);
    }

    @Test
    public void testLhsExtends() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Student s = new Student();
        s.setAge(15);
        kieSession.insert(s);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_lhs_"));
        kieSession.dispose();
        assertTrue(true);
    }
}
