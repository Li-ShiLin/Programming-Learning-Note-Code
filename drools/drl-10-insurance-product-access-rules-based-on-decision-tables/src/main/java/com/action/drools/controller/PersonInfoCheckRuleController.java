package com.action.drools.controller;

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
public class PersonInfoCheckRuleController {
    @Autowired
    private RuleService ruleService;

    @PostMapping("/personInfoCheck")
    public Map<String, Object> personInfoCheck(@RequestBody PersonInfoEntity personInfoEntity) {
        Map<String, Object> map = new HashMap<>();

        try {
            List<String> list = ruleService.personInfoCheck(personInfoEntity);
            if (list != null && !list.isEmpty()) {
                map.put("checkResult", false);
                map.put("msg", "校验失败");
                map.put("detail", list);
            } else {
                map.put("checkResult", true);
                map.put("msg", "校验通过");
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