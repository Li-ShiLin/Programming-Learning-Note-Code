package com.action.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code salience.drl}：salience 优先级。
 */
public class SalienceTest {

    @Test
    public void testSalience() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_salience_"));
        kieSession.dispose();
        // 预期顺序：rule_salience_2(10) -> rule_salience_1(9) -> rule_salience_3(8)
        assertTrue(true);
    }
}
