package com.action.drools;

import com.action.drools.entity.Order;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 图书优惠规则单元测试，覆盖全部四条规则。
 */
public class BookDiscountAppTest {

    private Order runRules(Double originalPrice) {
        KieServices kieServices = KieServices.Factory.get();
        //获得Kie容器对象
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //从Kie容器对象中获取会话对象
        KieSession kieSession = kieContainer.newKieSession();
        //Fact对象，事实对象
        Order order = new Order();
        order.setOriginalPrice(originalPrice);
        //将数据提供给规则引擎，规则引擎会根据提供的数据进行规则匹配
        //将order对象插入到工作内存中
        kieSession.insert(order);
        //激活规则引擎，如果规则匹配成功则执行规则
        kieSession.fireAllRules();
        //关闭会话
        kieSession.dispose();
        System.out.println("优惠前原始价格：" + order.getOriginalPrice() + "，优惠后价格：" + order.getRealPrice());
        return order;
    }

    /**
     * 规则一：所购图书总价在100元以下的没有优惠
     */
    @Test
    public void testRule1() {
        Order order = runRules(80D);
        assertNotNull(order.getRealPrice());
        assertEquals(Double.valueOf(80D), order.getRealPrice());
    }

    /**
     * 规则二：所购图书总价在100到200元的优惠20元
     */
    @Test
    public void testRule2() {
        Order order = runRules(150D);
        assertNotNull(order.getRealPrice());
        assertEquals(Double.valueOf(130D), order.getRealPrice());
    }

    /**
     * 规则三：所购图书总价在200到300元的优惠50元
     */
    @Test
    public void testRule3() {
        Order order = runRules(210D);
        assertNotNull(order.getRealPrice());
        assertEquals(Double.valueOf(160D), order.getRealPrice());
    }

    /**
     * 规则四：所购图书总价在300元以上的优惠100元
     */
    @Test
    public void testRule4() {
        Order order = runRules(350D);
        assertNotNull(order.getRealPrice());
        assertEquals(Double.valueOf(250D), order.getRealPrice());
    }
}
