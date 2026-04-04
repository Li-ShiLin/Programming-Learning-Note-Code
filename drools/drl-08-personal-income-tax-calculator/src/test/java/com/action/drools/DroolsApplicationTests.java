package com.action.drools;

import com.action.drools.entity.Calculation;
import com.action.drools.service.RuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DroolsApplicationTests {

    @Autowired
    private RuleService ruleService;

    @Test
    void contextLoads() {
    }

    /**
     * 教程示例：税前 10000，应纳税所得额 6500，税率 0.2，速算扣除 555，扣税 745，税后 9255
     */
    @Test
    void calculateTaxExampleFromTutorial() {
        Calculation c = new Calculation();
        c.setWage(10000);
        ruleService.calculate(c);
        assertEquals(6500, c.getWagemore(), 0.01);
        assertEquals(0.2, c.getCess(), 0.0001);
        assertEquals(555, c.getPreminus(), 0.01);
        assertEquals(745, c.getWageminus(), 0.01);
        assertEquals(9255, c.getActualwage(), 0.01);
    }
}
