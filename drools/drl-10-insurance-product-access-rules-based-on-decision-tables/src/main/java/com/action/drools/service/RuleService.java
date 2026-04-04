package com.action.drools.service;

import com.action.drools.entity.InsuranceInfo;
import com.action.drools.entity.PersonInfoEntity;
import com.action.drools.utils.KieSessionUtils;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RuleService {
    @Value("${drools.insurance.xls-path:classpath:rules/insuranceInfoCheck.xls}")
    private String insuranceRulesPath;
    @Value("${drools.person.xls-path:classpath:rules/testRule.xls}")
    private String personRulesPath;

    public List<String> insuranceInfoCheck(InsuranceInfo insuranceInfo) throws Exception {
        KieSession session = KieSessionUtils.getKieSessionFromXLS(insuranceRulesPath);
        session.getAgenda().getAgendaGroup("sign").setFocus();

        session.insert(insuranceInfo);

        List<String> listRules = new ArrayList<>();
        session.setGlobal("listRules", listRules);

        session.fireAllRules();
        session.dispose();

        return listRules;
    }

    public List<String> personInfoCheck(PersonInfoEntity personInfo) throws Exception {
        KieSession session = KieSessionUtils.getKieSessionFromXLS(personRulesPath);
        session.getAgenda().getAgendaGroup("sign").setFocus();

        session.insert(personInfo);

        List<String> listRules = new ArrayList<>();
        session.setGlobal("listRules", listRules);

        session.fireAllRules();
        session.dispose();

        return listRules;
    }
}