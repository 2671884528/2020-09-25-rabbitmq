package com.gyg.rabbit.service;

import com.gyg.rabbit.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class ProviderTest {


    @Test
    public void testSendMessage() {


        try {
            //获取连接
            Connection conn = ConnectionUtils.getConnection();
            //获取通道
            Channel channel = conn.createChannel();
            //绑定消息队列,不存在自动创建
            channel.queueDeclare("hello", false, false, false, null);
            //推送消息
            /**
             * exchange:要将消息发送到的Exchange(交换器)
             * routingKey:路由Key
             * mandatory:true 如果mandatory标记被设置
             * immediate: true 如果immediate标记被设置，注意：RabbitMQ服务端不支持此标记
             * props:其它的一些属性，如：{@link MessageProperties.PERSISTENT_TEXT_PLAIN}
             * body:消息内容
             **/
            channel.basicPublish("", "hello", null, "hello".getBytes());
           ConnectionUtils.closeConnection(channel,conn);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}