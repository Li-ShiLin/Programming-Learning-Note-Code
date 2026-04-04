package com.action.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code agendagroup.drl}：agenda-group 与 auto-focus 属性。
 */
public class AgendaGroupTest {

    @Test
    public void testAgendaGroup() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        // 设置焦点，对应 agenda-group 分组中的规则才可能被触发
        kieSession.getAgenda().getAgendaGroup("myagendagroup_1").setFocus();
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_agendagroup_"));
        kieSession.dispose();
        // 只有 myagendagroup_1 中规则触发
        assertTrue(true);
    }

    @Test
    public void testAutoFocus() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_agendagroup_"));
        kieSession.dispose();
        // myagendagroup_2 设置了 auto-focus true，其规则会触发
        assertTrue(true);
    }
}
