package com.action.drools;

import com.action.drools.service.UserService;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 对应 {@code global.drl}：global 全局变量。
 */
public class GlobalTest {

    @Test
    public void testGlobal() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        KieSession kieSession = kieContainer.newKieSession();

        // 名称和类型须与规则文件中 global 声明一致
        kieSession.setGlobal("userService", new UserService());
        kieSession.setGlobal("count", Integer.valueOf(5));
        List list = new ArrayList();
        kieSession.setGlobal("gList", list);

        kieSession.fireAllRules(AgendaFilters.filterByPrefix("rule_global_"));
        kieSession.dispose();

        assertEquals(2, list.size());
    }
}
