package com.gyg.rabbit.fanout;

import com.gyg.rabbit.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;

/**
 * @auther 郭永钢
 * @data 2020/9/24 18:50
 * @desc: 广播模式
 */

public class Provider {

    @Test
    public void testMessage() {
        try {
            Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();

            //There are a few exchange types available: direct, topic, headers and fanout. We'll focus on the last one
            // -- the fanout. Let's create an exchange of this type, and call it logs:
            channel.exchangeDeclare("logs","fanout");


            channel.basicPublish("logs","",null,"hello".getBytes());
            ConnectionUtils.closeConnection(channel, connection);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
