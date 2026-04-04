package com.action.drools;

import com.action.drools.entity.Order;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Drools 入门案例：图书优惠规则演示。
 * 根据订单原始价格，通过规则引擎计算优惠后价格。
 */
public class BookDiscountApp {

    public static void main(String[] args) {
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession kieSession = kieContainer.newKieSession();
        //Fact对象，事实对象
        Order order = new Order();
        order.setOriginalPrice(210D);
        //将order对象插入到工作内存中
        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();

        System.out.println("优惠前原始价格：" + order.getOriginalPrice() + "，优惠后价格：" + order.getRealPrice());
    }
}
