package com.atguigu.gulimall.order.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MyRabbitConfig {
    /**
     * 使用JSON序列化机制，进行消息转换
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 定制RabbitTemplate
     */
    @PostConstruct //MyRabbitConfig对象创建完成以后，执行这个方法
    public void initRabbitTemplate() {
        //设置确认回调
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             *
             * 1、只要消息成功抵达Broker，则ack=true
             * @param correlationData 当前消息的唯一关联数据（可以看成是消息的唯一id），可以在发送消息时对correlationData进行指定
             * @param ack  消息是否成功收到
             * @param cause 失败的原因
             */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                /**
                 * 1、做好消息确认机制（pulisher，consumer【手动ack】）
                 * 2、每一个发送的消息都在数据库做好记录。定期将失败的消息再次发送一遍
                 */
                //服务器收到了；
                //修改消息的状态
                System.out.println("confirm...correlationData[" + correlationData + "]==>ack[" + ack + "]==>cause[" + cause + "]");
                // confirm...correlationData[CorrelationData [id=f4a53afd-dce0-45b1-85b5-db5bd7754e14]]==>ack[true]==>cause[null]
            }
        });
        //设置消息抵达队列的确认回调
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            /**
             * 只要消息没有投递给指定的队列（投递失败），就触发这个失败回调
             * @param message   投递失败的消息详细信息
             * @param replyCode 服务端回复的状态码
             * @param replyText 服务端回复的文本内容
             * @param exchange  当时这个消息发给哪个交换机
             * @param routingKey 当时这个消息用哪个路由键
             */
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                //报错误了。修改数据库当前消息的状态->错误。
                System.out.println("==>Fail Message[" + message + "]");
                System.out.println("==>replyCode[" + replyCode + "]");
                System.out.println("==>replyText[" + replyText + "]");
                System.out.println("==>exchange[" + exchange + "]");
                System.out.println("==>routingKey[" + routingKey + "]");
            }
        });
    }
}
