package com.example.consumer;

import com.example.api.PaymentService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.List;

public class ConsumerApp {
    public static void main(String[] args) {
        // 获取扩展点加载器
        ExtensionLoader<PaymentService> loader =
                ExtensionLoader.getExtensionLoader(PaymentService.class);

        System.out.println("===== 1. 基本SPI使用 =====");
        basicSpiDemo(loader);

        System.out.println("\n===== 2. 自适应扩展点演示 =====");
        adaptiveExtensionDemo(loader);

        System.out.println("\n===== 3. 自动激活扩展点演示 =====");
        activateExtensionDemo(loader);

        System.out.println("\n===== 4. 依赖注入演示 =====");
        dependencyInjectionDemo(loader);

        System.out.println("\n===== 5. 包装器演示 =====");
        wrapperDemo(loader);
    }

    /**
     * 基本SPI使用
     *
     * @param loader
     */
    private static void basicSpiDemo(ExtensionLoader<PaymentService> loader) {
        // 获取所有支持的扩展点
        System.out.println("支持的扩展点: " + loader.getSupportedExtensions());

        // 按名称获取扩展点实例
        PaymentService alipay = loader.getExtension("alipay");
        alipay.pay(100.0);

        PaymentService wechat = loader.getExtension("wechat");
        wechat.pay(200.0);

        // 获取默认扩展点
        PaymentService defaultService = loader.getDefaultExtension();
        System.out.println("默认扩展点: " + defaultService.getProviderName());
        /*输出
        ===== 1. 基本SPI使用 =====
        支持的扩展点: [alipay, wechat]
        [Alipay] 支付成功: ¥100.00
        [Logger] Alipay支付日志: 100.0
        [WeChat Pay] 付款完成: ¥200.00
        [Logger] Wechat支付日志: 200.0
        默认扩展点: alipay
         */
    }


    /**
     * 自适应扩展点演示
     *
     * @param loader
     */
    private static void adaptiveExtensionDemo(ExtensionLoader<PaymentService> loader) {
        // 获取自适应扩展
        PaymentService adaptiveService = loader.getAdaptiveExtension();

        // 通过URL参数指定使用wechat
        URL wechatUrl = URL.valueOf("test://localhost/demo?payment.service=wechat&scene=mobile");
        adaptiveService.adaptivePay(150.0, wechatUrl);

        // 通过URL参数指定使用alipay
        URL alipayUrl = URL.valueOf("test://localhost/demo?payment.service=alipay&scene=online");
        adaptiveService.adaptivePay(250.0, alipayUrl);

        // 不指定时使用默认扩展点
        URL defaultUrl = URL.valueOf("test://localhost/demo");
        adaptiveService.adaptivePay(350.0, defaultUrl);
        /*输出
        ===== 2. 自适应扩展点演示 =====
        [WeChat Adaptive] 支付完成: ¥150.00 (场景: mobile)
        [Alipay Adaptive] 支付成功: ¥250.00 (场景: online)
        [Alipay Adaptive] 支付成功: ¥350.00 (场景: default)
         */
    }


    /**
     * 自动激活扩展点演示
     *
     * @param loader
     */
    private static void activateExtensionDemo(ExtensionLoader<PaymentService> loader) {
        // 模拟URL参数，包含group="mobile"
        URL mobileUrl = URL.valueOf("test://localhost/demo?group=mobile");
        List<PaymentService> mobileServices = loader.getActivateExtension(mobileUrl, "group");
        System.out.println("\n移动端激活的支付服务:");
        mobileServices.forEach(service -> {
            service.pay(50.0);
        });

        // 模拟URL参数，包含group="online"
        URL onlineUrl = URL.valueOf("test://localhost/demo?group=online");
        List<PaymentService> onlineServices = loader.getActivateExtension(onlineUrl, "group");
        System.out.println("\n在线支付激活的支付服务:");
        onlineServices.forEach(service -> {
            service.pay(100.0);
        });
        /*输出
        ===== 3. 自动激活扩展点演示 =====

        移动端激活的支付服务:
        [Alipay] 支付成功: ¥50.00
        [Logger] Alipay支付日志: 50.0
        [WeChat Pay] 付款完成: ¥50.00
        [Logger] Wechat支付日志: 50.0

        在线支付激活的支付服务:
        [Alipay] 支付成功: ¥100.00
        [Logger] Alipay支付日志: 100.0
        [WeChat Pay] 付款完成: ¥100.00
        [Logger] Wechat支付日志: 100.0
         */
    }


    /**
     * 依赖注入演示
     *
     * @param loader
     */
    private static void dependencyInjectionDemo(ExtensionLoader<PaymentService> loader) {
        // Dubbo会自动注入依赖，这里我们验证日志功能
        PaymentService alipay = loader.getExtension("alipay");
        alipay.pay(123.45); // 查看控制台输出，会有日志记录

        PaymentService wechat = loader.getExtension("wechat");
        wechat.pay(678.90); // 查看控制台输出，会有日志记录
        /*输出
        ===== 4. 依赖注入演示 =====
        [Alipay] 支付成功: ¥123.45
        [Logger] Alipay支付日志: 123.45
        [WeChat Pay] 付款完成: ¥678.90
        [Logger] Wechat支付日志: 678.9
         */
    }


    /**
     * 包装器演示
     *
     * @param loader
     */
    private static void wrapperDemo(ExtensionLoader<PaymentService> loader) {
        // 获取所有支持退款的支付服务
        System.out.println("\n支持退款的服务:");
        loader.getSupportedExtensions().forEach(name -> {
            PaymentService service = loader.getExtension(name);
            if (service.supportRefund()) {
                System.out.println(" - " + service.getProviderName());
                service.refund(75.0);
            }
        });
        /*输出：
        ===== 5. 包装器演示 =====

        支持退款的服务:
         - alipay
        [Alipay] 退款成功: ¥75.00
         - wechat
        [WeChat Pay] 退款完成: ¥75.00
         */
    }
}