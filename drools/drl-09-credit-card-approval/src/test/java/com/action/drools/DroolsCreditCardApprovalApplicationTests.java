package com.action.drools;

import com.action.drools.entity.CreditCardApplyInfo;
import com.action.drools.service.RuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class DroolsCreditCardApprovalApplicationTests {

    @Autowired
    private RuleService ruleService;

    @Test
    void contextLoads() {
    }

    /**
     * 合法性：没房没车 + 专科以下 + 月薪&lt;5000 → 不通过
     */
    @Test
    void rejectWhenNoHouseNoCarEducation1AndLowIncome() {
        CreditCardApplyInfo info = new CreditCardApplyInfo();
        info.setEducation(CreditCardApplyInfo.EDUCATION_1);
        info.setMonthlyIncome(4000);
        info.setHasHouse(false);
        info.setHasCar(false);
        ruleService.creditCardApply(info);
        assertFalse(info.isCheckResult());
    }

    /**
     * 合法性：信用卡&gt;10 → 不通过
     */
    @Test
    void rejectWhenTooManyCreditCards() {
        CreditCardApplyInfo info = new CreditCardApplyInfo();
        info.setHasCreditCardCount(11);
        ruleService.creditCardApply(info);
        assertFalse(info.isCheckResult());
    }

    /**
     * 额度：有房有车 → 15000
     */
    @Test
    void quota15000WhenHouseAndCar() {
        CreditCardApplyInfo info = new CreditCardApplyInfo();
        info.setHasHouse(true);
        info.setHasCar(true);
        info.setMonthlyIncome(5000);
        ruleService.creditCardApply(info);
        assertTrue(info.isCheckResult());
        assertEquals(15000, info.getQuota(), 0.01);
    }

    /**
     * 额度：没房没车、月收入 10000～20000 → 6000
     */
    @Test
    void quota6000WhenNoHouseNoCarIncomeInRange() {
        CreditCardApplyInfo info = new CreditCardApplyInfo();
        info.setHasHouse(false);
        info.setHasCar(false);
        info.setMonthlyIncome(15000);
        ruleService.creditCardApply(info);
        assertTrue(info.isCheckResult());
        assertEquals(6000, info.getQuota(), 0.01);
    }
}
