package com.action.drools;

import com.action.drools.entity.Student;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code studentInsert.drl}：内置方法 {@code insert}。
 */
public class StudentInsertTest {

    @Test
    public void testStudentInsert() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();

        Student student = new Student();
        student.setAge(10);
        kieSession.insert(student);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_student_insert_"));
        kieSession.dispose();
        assertTrue(true);
    }
}
