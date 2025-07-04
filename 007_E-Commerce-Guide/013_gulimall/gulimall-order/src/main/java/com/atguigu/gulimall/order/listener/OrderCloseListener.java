package com.atguigu.gulimall.order.listener;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.service.OrderService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@RabbitListener(queues = "order.release.order.queue")
@Service
public class OrderCloseListener {
    @Autowired
    OrderService orderService;
    @RabbitHandler
    public void listener(OrderEntity entity, Channel channel, Message message) throws IOException {
        System.out.println("收到过期的订单信息：准备关闭订单" + entity.getOrderSn() + "==>" + entity.getId());
        try {
            orderService.closeOrder(entity);
            //手动调用支付宝收单；
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            // 关闭订单失败的话拒绝消息，让消息重新回到消息队列，而不是被丢弃掉
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }
    }
}
