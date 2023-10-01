package com.atguigu.gulimall.order.web;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Controller
public class HelloController {

//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//
//    // 向消息队列order.release.order.queue发送消息
//    @ResponseBody
//    @GetMapping("/test/createOrder")
//    public String createOrderTest() {
//        //订单下单成功
//        OrderEntity entity = new OrderEntity();
//        entity.setOrderSn(UUID.randomUUID().toString());
//        entity.setModifyTime(new Date());
//
//        //给MQ发送消息。
//        rabbitTemplate.convertAndSend("order-event-exchange", "order.create.order", entity);
//        return "ok";
//    }
//
//    // 监听消息队列order.release.order.queue消费消息
//    @RabbitListener(queues = "order.release.order.queue")
//    public void listener(OrderEntity entity, Channel channel, Message message) throws IOException {
//        System.out.println("收到过期的订单消息，准备关闭订单");
//        System.out.println("订单号： " + entity.getOrderSn());
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//    }


    @GetMapping("/{page}.html")
    public String listPage(@PathVariable("page") String page) {
        System.out.println(page);
        return page;
    }
}
