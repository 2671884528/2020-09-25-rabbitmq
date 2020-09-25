package com.gyg.rabbit.workquene;

import com.gyg.rabbit.utils.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;

import java.io.IOException;

/**
 * @auther 郭永钢
 * @data 2020/9/24 18:50
 * @desc:
 */

public class Provider {

    @Test
    public void testMessage(){
        try {
            Connection connection = ConnectionUtils.getConnection();
            Channel channel = connection.createChannel();

            //队列名，队列持久化，队列独占，消费后自动删除，参数
            channel.queueDeclare("workqueue", true, false, false, null);

            for (int i = 0; i < 20; i++) {

                channel.basicPublish("", "workqueue", null, ("hello"+i).getBytes());
            }
            ConnectionUtils.closeConnection(channel,connection);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
