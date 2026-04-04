package com.action.drools;

import com.action.drools.entity.Customer;
import com.action.drools.entity.Order;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code patternMatch.drl}：Pattern 模式匹配。
 */
public class PatternMatchTest {

    @Test
    public void testPatternMatch() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Order order = new Order();
        order.setOriginalPrice(150D);
        kieSession.insert(order);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_pattern_"));
        kieSession.dispose();
        assertTrue(order.getRealPrice() != null && order.getRealPrice() == 130D);
    }

    @Test
    public void testPatternMatchMulti() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Order order = new Order();
        order.setOriginalPrice(150D);
        Customer customer = new Customer();
        customer.setAge(25);
        customer.setGender("male");
        kieSession.insert(order);
        kieSession.insert(customer);

        kieSession.fireAllRules(AgendaFilters.filterByRuleName("rule_pattern_multi"));
        kieSession.dispose();
        assertTrue(order.getRealPrice() != null && order.getRealPrice() == 130D);
    }
}
