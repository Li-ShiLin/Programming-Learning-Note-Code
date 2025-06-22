package com.example.wechat;

import com.example.api.PaymentService;
import com.example.common.LogWrapper;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Activate;

@Activate(group = {"mobile"}, order = 2) // 自动激活扩展
public class WechatPayService implements PaymentService {

    private final LogWrapper logger;

    // 无参构造函数，供Dubbo SPI使用
    public WechatPayService() {
        this.logger = new LogWrapper();
    }

    // 带参构造函数，用于手动创建实例
    public WechatPayService(LogWrapper logger) {
        this.logger = logger;
    }

    @Override
    public void pay(double amount) {
        System.out.printf("[WeChat Pay] 付款完成: ¥%.2f%n", amount);
        logger.log("Wechat支付日志: " + amount);
    }

    @Override
    public String getProviderName() {
        return "wechat";
    }

    @Override
    public void adaptivePay(double amount, URL url) {
        String scene = url.getParameter("scene", "default");
        System.out.printf("[WeChat Adaptive] 支付完成: ¥%.2f (场景: %s)%n", amount, scene);
    }

    @Override
    public boolean supportRefund() {
        return true;
    }

    @Override
    public void refund(double amount) {
        System.out.printf("[WeChat Pay] 退款完成: ¥%.2f%n", amount);
    }
}