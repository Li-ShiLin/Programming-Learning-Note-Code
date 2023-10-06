package com.atguigu.gulimall.order;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.atguigu.gulimall.order.entity.OrderReturnReasonEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;


@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitMQTest {

    @Autowired
    private AmqpAdmin amqpAdmin;

    /**
     * 创建交换机Exchange
     * 使用AmqpAdmin创建名为hello-java-exchange的交换机Exchange
     */
    @Test
    public void createExchange() {
        // 构造器DirectExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments)
        // DirectExchange构造器参数：交换机名称、是否持久化、是否自动删除
        DirectExchange directExchange = new DirectExchange("hello-java-exchange", true, false);
        amqpAdmin.declareExchange(directExchange);
        log.info("Exchange[{}]创建成功", "hello-java-exchange");
    }

    /**
     * 创建消息队列Queue
     * 使用AmqpAdmin创建名为hello-java-queue的消息队列Queue
     */
    @Test
    public void createQueue() {
        // 构造器：public Queue(String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
        // Queue的构造参数：队列名、是否持久、是否排他、是否自动删除、自定义参数
        Queue queue = new Queue("hello-java-queue", true, false, false); // org.springframework.amqp.core.Queue包中的queue
        amqpAdmin.declareQueue(queue);
        log.info("Queue[{}]创建成功", "hello-java-queue");
    }

    /**
     * 创建绑定关系Binding
     */
    @Test
    public void createBinding() {
        //  Binding构造器参数：目的地、目的地类型、交换机、路由键、自定义参数
        //  public Binding(String destination, DestinationType destinationType, String exchange, String routingKey, Map<String, Object> arguments)
        //将exchange指定的交换机和destination目的地进行绑定，使用routingKey作为指定的路由键
        Binding binding = new Binding("hello-java-queue", Binding.DestinationType.QUEUE, "hello-java-exchange", "hello.java", null);
        amqpAdmin.declareBinding(binding);
        log.info("Binding[{}]创建成功", "hello-java-binding");
    }


    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 使用RabbitMQ收发消息
     */
    @Test
    public void sendMessages() {
//        //1、发送消息
//        String msg = "Hello World!";
//        rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", msg);

        // 2、如果发送的消息是个对象，我们会使用序列化机制，将对象写出去。对象必须实现Serializable
        // 发送的对象类型的消息，可以转成一个json
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                OrderReturnReasonEntity reasonEntity = new OrderReturnReasonEntity();
                reasonEntity.setId(1L);
                reasonEntity.setCreateTime(new Date());
                reasonEntity.setName("哈哈-" + i);
                rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", reasonEntity, new CorrelationData(UUID.randomUUID().toString()));
            } else {
                OrderEntity entity = new OrderEntity();
                entity.setOrderSn(UUID.randomUUID().toString());
                rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java", entity, new CorrelationData(UUID.randomUUID().toString()));
            }
        }
        log.info("消息发送完成");
    }

}
