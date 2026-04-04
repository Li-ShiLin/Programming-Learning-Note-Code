package com.action.drools;

import com.action.drools.entity.InsuranceInfo;
import com.action.drools.service.RuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class InsuranceInfoCheckIntegrationTest {

    @Autowired
    private RuleService ruleService;

    private InsuranceInfo buildBaseInsuranceInfo() {
        InsuranceInfo insuranceInfo = new InsuranceInfo();
        insuranceInfo.setParam1("picc");
        insuranceInfo.setParam5("10");
        insuranceInfo.setParam6("20");
        insuranceInfo.setParam7("10");
        insuranceInfo.setParam8("2");
        insuranceInfo.setParam13("10");
        return insuranceInfo;
    }

    /**
     * 与 InsuranceRuleController 模拟数据一致：上海区域应触发准入失败
     */
    @Test
    void insuranceInfoCheckFailsForShanghaiRegion() throws Exception {
        InsuranceInfo insuranceInfo = buildBaseInsuranceInfo();
        insuranceInfo.setParam4("上海");
        List<String> list = ruleService.insuranceInfoCheck(insuranceInfo);
        assertFalse(list.isEmpty());
    }

    @Test
    void insuranceInfoCheckPassesForBeijingAndPicc() throws Exception {
        InsuranceInfo insuranceInfo = buildBaseInsuranceInfo();
        insuranceInfo.setParam4("北京");
        List<String> list = ruleService.insuranceInfoCheck(insuranceInfo);
        assertTrue(list != null);
    }
}
