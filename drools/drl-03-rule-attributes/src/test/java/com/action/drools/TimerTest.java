package com.action.drools;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertTrue;

/**
 * 对应 {@code timer.drl}：timer 属性。
 */
public class TimerTest {

    @Test
    public void testTimer() throws Exception {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        final KieSession kieSession = kieContainer.newKieSession();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 启动规则引擎进行规则匹配，直到调用 halt 才结束（仅执行 timer 规则）
                kieSession.fireUntilHalt(AgendaFilters.filterByPrefix("rule_timer_"));
            }
        }).start();

        Thread.sleep(10000);
        kieSession.halt();
        kieSession.dispose();
        assertTrue(true);
    }
}
