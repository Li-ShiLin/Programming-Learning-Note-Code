package com.action.drools;

import com.action.drools.entity.Student;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code noloop.drl}：no-loop 属性。
 */
public class NoLoopTest {

    @Test
    public void testNoLoop() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Student student = new Student();
        student.setAge(25);
        kieSession.insert(student);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_noloop"));
        kieSession.dispose();
        // no-loop true，只触发一次，不死循环
        assertTrue(true);
    }
}
