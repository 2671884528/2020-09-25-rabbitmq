package com.gyg.rabbit.workqueue2.workquene;

import com.gyg.rabbit.utils.ConnectionUtils;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;

/**
 * @auther 郭永钢
 * @data 2020/9/24 19:02
 * @desc:
 */

public class Custom1 {
    @Test
    public void customerTest() {

        try {
            Connection conn = ConnectionUtils.getConnection();

            Channel channel = conn.createChannel();
            //设置通道
            channel.queueDeclare("workqueue", true, false, false, null);
            //
            channel.basicConsume("workqueue", false, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("++++++++++++" + new String(body));
                    //参数1：消费的消息标识，参数2：是否接收多个消息签收
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            });
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
