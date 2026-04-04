package com.action.drools;

import com.action.drools.entity.Student;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code studentUpdate.drl}：内置方法 {@code update}。
 */
public class StudentUpdateTest {

    @Test
    public void testStudentUpdate() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieClasspathContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieClasspathContainer.newKieSession();

        Student student = new Student();
        student.setAge(5);
        kieSession.insert(student);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_student_update_"));
        kieSession.dispose();
        assertTrue(true);
    }
}
