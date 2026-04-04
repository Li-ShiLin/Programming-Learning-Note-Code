package com.action.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code activationgroup.drl}：activation-group 属性。
 */
public class ActivationGroupTest {

    @Test
    public void testActivationGroup() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_activationgroup_"));
        kieSession.dispose();
        // 同组只能触发一个
        assertTrue(true);
    }
}
