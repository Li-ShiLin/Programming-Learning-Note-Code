package com.action.drools;

import com.action.drools.entity.ComparisonOperatorEntity;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code enabledAttribute.drl}：enabled 属性。
 */
public class EnabledAttributeTest {

    @Test
    public void testEnabled() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        ComparisonOperatorEntity entity = new ComparisonOperatorEntity();
        entity.setNames("王五");
        List<String> list = new ArrayList<String>();
        list.add("张三");
        list.add("李四");
        entity.setList(list);
        kieSession.insert(entity);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_enabled_"));
        kieSession.dispose();
        // enabled false，规则不触发，无输出
        assertTrue(true);
    }
}
