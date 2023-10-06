package com.atguigu.gulimall.order.service.impl;

import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;
import com.atguigu.gulimall.order.dao.OrderItemDao;
import com.atguigu.gulimall.order.entity.OrderItemEntity;
import com.atguigu.gulimall.order.entity.OrderReturnReasonEntity;
import com.atguigu.gulimall.order.service.OrderItemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@RabbitListener(queues = {"hello-java-queue"})
@Service("orderItemService")
public class OrderItemServiceImpl extends ServiceImpl<OrderItemDao, OrderItemEntity> implements OrderItemService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<OrderItemEntity> page = this.page(new Query<OrderItemEntity>().getPage(params), new QueryWrapper<OrderItemEntity>());

        return new PageUtils(page);
    }


//    /**
//     * @RabbitListener注解: 监听RabbitMQ中的消息
//     * queues：指明要监听的队列，可以监听多个消息队列
//     * Object message：封装接收到的消息
//     */
//    @RabbitListener(queues = {"hello-java-queue"})
//    public void receiveMessage(Object message) {
//        System.out.println("接收到消息...内容：" + message + "  消息的类型：" + message.getClass()); // 消息的类型： org.springframework.amqp.core.Message
//    }


//    /**
//     * @RabbitListener注解: 监听RabbitMQ中的消息
//     * queues：指明要监听的队列，可以监听多个消息队列
//     * 接收消息的参数可以写成Message message：原生消息详细信息。包含 消息头、消息体等
//     */
//    @RabbitListener(queues = {"hello-java-queue"})
//    public void receiveMessage(Message message) {
//        // 消息体： {"id":1,"name":"哈哈","sort":null,"status":null,"createTime":1581144531744}
//        byte[] body = message.getBody();
//        // 消息头属性信息
//        MessageProperties messageProperties = message.getMessageProperties();
//        // MessageProperties [headers={__TypeId__=com.atguigu.gulimall.order.entity.OrderEntity}, contentType=application/json, contentEncoding=UTF-8, contentLength=0, receivedDeliveryMode=PERSISTENT, priority=0, redelivered=false, receivedExchange=hello-java-exchange, receivedRoutingKey=hello.java, deliveryTag=6, consumerTag=amq.ctag-CBRxfqVOLXqCdoTy5a9Q5Q, consumerQueue=hello-java-queue]
//        System.out.println("消息体：" + body + "  消息头属性： " + messageProperties);
//        System.out.println("接收到消息...内容：" + message + "  消息的类型：" + message.getClass());
//    }


//    /**
//     * @RabbitListener注解: 监听RabbitMQ中的消息
//     * queues：指明要监听的队列，可以监听多个消息队列
//     * 接收消息的参数也可以直接写成发送的消息体的类型： OrderEntity content
//     */
//    @RabbitListener(queues = {"hello-java-queue"})
//    public void receiveMessage(Message message, OrderEntity content) {
//        System.out.println("接收到消息：" + message);
//        System.out.println("接收到的消息内容: " + content);
//        // 接收到的消息内容: OrderEntity(id=1, memberId=null, orderSn=null, couponId=null, memberUsername=null, totalAmount=null, payAmount=null, freightAmount=null
//    }


//    /**
//     * @RabbitListener注解: 监听RabbitMQ中的消息
//     * queues：指明要监听的队列，可以监听多个消息队列
//     * 监听消息队列的方法的参数也可以写成Channel channel：当前传输数据的通道
//     * Channel类型：  com.rabbitmq.client.Channel
//     */
//    @RabbitListener(queues = {"hello-java-queue"})
//    public void receiveMessage(Message message, OrderEntity content, Channel channel) {
//        System.out.println("接收到消息：" + message);
//        System.out.println("接收到的消息内容: " + content);
//        // 接收到的消息内容: OrderEntity(id=1, memberId=null, orderSn=null, couponId=null, memberUsername=null, totalAmount=null, payAmount=null, freightAmount=null
//    }


//    /**
//     * 可以同时允许很多人监听消息队列。只要接收到消息，队列中的消息就会被删除，而且只能有一个人接收到消息
//     * 场景演示：
//     *          1）订单服务启动多个，同一个消息，只能有一个客户端服务接收到
//     *          2) 只有一个消息完全处理完，方法运行结束，我们才可以接收到下一个消息
//     */
//    @RabbitListener(queues = {"hello-java-queue"})
//    public void receiveMessage(Message message, OrderReturnReasonEntity content, Channel channel) throws InterruptedException {
//        System.out.println("接收到消息：" + message);
//        System.out.println("接收到的消息内容: " + content);
//        Thread.sleep(3000);
//        System.out.println("消息处理完成......");
//    }


//    // 演示@RabbiHander的方法重载
//    @RabbitHandler
//    public void receiveMessage(OrderReturnReasonEntity content)  {
//        System.out.println("receiveMessage方法接收到的消息内容: " + content);
//    }
//
//    @RabbitHandler
//    public void recieveMessage2(OrderEntity content) {
//        System.out.println("recieveMessage2方法接收到的消息内容："+content);
//    }


    /*
      消费端确认机制：手动确认（保证每个消息被正确消费，此时才让broker删除这个消息
              spring.rabbitmq.listener.simple.acknowledge-mode=manual 手动签收
              1、默认是自 动确认的，只要消息接收到，客户端会自动确认，服务端就会移除这个消息
                  问题：
                    我们收到很多消息，自动回复给服务器ack，只有一个消息处理成功，宕机了。就会发生消息丢失；
                    消费者手动确认模式。只要我们没有明确告诉MQ，货物被签收。没有Ack，
                    消息就一直是unacked状态。即使Consumer宕机。消息不会丢失，会重新变为Ready，下一次有新的Consumer连接进来就发给他
              2、如何签收:
                   channel.basicAck(deliveryTag,false);签收；业务成功完成就应该签收
                   channel.basicNack(deliveryTag,false,true);拒签；业务失败，拒签
     */
    @RabbitListener(queues = {"hello-java-queue"})
    public void receiveMessage(Message message, OrderReturnReasonEntity content, Channel channel) {
        System.out.println("接收到消息：" + message);
        // channel内按顺序自增的
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("deliveryTag==>" + deliveryTag);

        try {
            if (deliveryTag % 2 == 0) {
                //签收货物，发送ack，非批量模式
                channel.basicAck(deliveryTag, false);
                System.out.println("签收了货物..." + deliveryTag);
            } else {
                //long deliveryTag, boolean multiple, boolean requeue
                // 拒签basicNack方法的参数：标签deliveryTag，是否批量，拒签的消息是否重新入队
                //退货 requeue=false 丢弃  requeue=true 发回服务器，服务器重新入队。
                channel.basicNack(deliveryTag, false, false);
                //long deliveryTag, boolean requeue
//                channel.basicReject();
                System.out.println("没有签收了货物..." + deliveryTag);
            }

        } catch (Exception e) {
            //网络中断
        }
    }

}