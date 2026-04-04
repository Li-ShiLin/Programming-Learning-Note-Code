package com.action.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code droolsMethods.drl}：{@code drools.getWorkingMemory()}、{@code drools.getRule()}。
 */
public class DroolsMethodsTest {

    @Test
    public void testDroolsMethods() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_get"));
        kieSession.dispose();
        assertTrue(true);
    }
}
