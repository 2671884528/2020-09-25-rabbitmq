package com.gyg.rabbit.service;

import com.gyg.rabbit.utils.ConnectionUtils;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @auther 郭永钢
 * @data 2020/9/24 16:05
 * @desc:
 */

public class CustTest {

    @Test
    public void customerTest() {

        try {
            Connection conn = ConnectionUtils.getConnection();

            Channel channel = conn.createChannel();
            //设置通道
            channel.queueDeclare("hello", false, false, false, null);
            //
            channel.basicConsume("hello", true, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("++++++++++++" + new String(body));
                }
            });
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
