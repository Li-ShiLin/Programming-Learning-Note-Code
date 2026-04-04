package com.action.drools;

import com.action.drools.entity.Student;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code function.drl}：function 函数。
 */
public class FunctionTest {

    @Test
    public void testFunction() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Student student = new Student();
        student.setName("小明");
        kieSession.insert(student);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_function_"));
        kieSession.dispose();
        assertTrue(true);
    }
}
