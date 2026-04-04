package com.action.drools.controller;

import com.action.drools.entity.InsuranceInfo;
import com.action.drools.entity.PersonInfoEntity;
import com.action.drools.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rule")
public class InsuranceRuleController {
    @Autowired
    private RuleService ruleService;

    @RequestMapping("/insuranceInfoCheckTest")
    public Map insuranceInfoCheckTest() {
        Map map = new HashMap();
        //模拟数据，实际应为页面传递过来
        InsuranceInfo insuranceInfo = new InsuranceInfo();
        insuranceInfo.setParam1("picc");
        insuranceInfo.setParam4("上海");
        insuranceInfo.setParam5("101");
        insuranceInfo.setParam6("12");
        insuranceInfo.setParam7("222");
        insuranceInfo.setParam8("1");
        insuranceInfo.setParam13("3");

        try {
            List<String> list = ruleService.insuranceInfoCheck(insuranceInfo);
            if (list != null && list.size() > 0) {
                map.put("checkResult", false);
                map.put("msg", "准入失败");
                map.put("detail", list);
            } else {
                map.put("checkResult", true);
                map.put("msg", "准入成功");
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("checkResult", false);
            map.put("msg", "未知错误");
            return map;
        }
    }


    @PostMapping("/insuranceInfoCheck")
    public Map<String, Object> insuranceInfoCheck(@RequestBody InsuranceInfo insuranceInfo) {
        Map<String, Object> map = new HashMap<>();

        try {
            List<String> list = ruleService.insuranceInfoCheck(insuranceInfo);
            if (list != null && !list.isEmpty()) {
                map.put("checkResult", false);
                map.put("msg", "准入失败");
                map.put("detail", list);
            } else {
                map.put("checkResult", true);
                map.put("msg", "准入成功");
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            map.put("checkResult", false);
            map.put("msg", "未知错误");
            return map;
        }
    }

}