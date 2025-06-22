package com.example;

import com.example.spi.PaymentService;

import java.util.ServiceLoader;

public class PaymentApp {
    public static void main(String[] args) {
        ServiceLoader<PaymentService> services = ServiceLoader.load(PaymentService.class);

        System.out.println("===== 发现的服务提供者 =====");
        services.forEach(provider -> System.out.println("提供者: " + provider.getProviderName()));

        System.out.println("\n===== 执行支付操作 =====");
        services.forEach(provider -> provider.pay(199.99));

        // 动态选择实现
        System.out.println("\n===== 动态选择实现 =====");
        useService("Alipay");
        /*输出：
        ===== 发现的服务提供者 =====
        提供者: Alipay
        提供者: WeChat Pay

        ===== 执行支付操作 =====
        [Alipay] 支付成功: ¥199.99
        [WeChat Pay] 付款完成: ¥199.99

        ===== 动态选择实现 =====
        [Alipay] 支付成功: ¥88.88
         */
    }

    // 扩展演示：动态选择实现
    // 在PaymentApp中添加
    // 调用示例
    //    useService("Alipay");  // 指定使用支付宝
    public static void useService(String providerName) {
        ServiceLoader<PaymentService> services = ServiceLoader.load(PaymentService.class);
        for (PaymentService service : services) {
            if (service.getProviderName().equalsIgnoreCase(providerName)) {
                service.pay(88.88);
                return;
            }
        }
        throw new IllegalArgumentException("未找到服务: " + providerName);
    }

}