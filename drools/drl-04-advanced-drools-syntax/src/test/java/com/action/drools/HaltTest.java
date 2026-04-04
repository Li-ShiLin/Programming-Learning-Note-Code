package com.action.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code halt.drl}：RHS 中 {@code drools.halt()}。
 */
public class HaltTest {

    @Test
    public void testHalt() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_halt_"));
        kieSession.dispose();
        assertTrue(true);
    }
}
