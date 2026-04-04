package com.action.drools;

import com.action.drools.entity.Student;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;

import static org.junit.Assert.assertEquals;

/**
 * 对应 {@code query.drl}：query 查询。
 */
public class QueryTest {

    @Test
    public void testQuery() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        Student student1 = new Student();
        student1.setName("张三");
        student1.setAge(12);

        Student student2 = new Student();
        student2.setName("李四");
        student2.setAge(8);

        Student student3 = new Student();
        student3.setName("王五");
        student3.setAge(22);

        kieSession.insert(student1);
        kieSession.insert(student2);
        kieSession.insert(student3);

        QueryResults results1 = kieSession.getQueryResults("query_1");
        int size = results1.size();
        assertEquals(2, size);
        for (QueryResultsRow row : results1) {
            Student student = (Student) row.get("$student");
            System.out.println(student);
        }

        QueryResults results2 = kieSession.getQueryResults("query_2", "王五");
        size = results2.size();
        assertEquals(1, size);
        for (QueryResultsRow row : results2) {
            Student student = (Student) row.get("$student");
            System.out.println(student);
        }
        kieSession.dispose();
    }
}
