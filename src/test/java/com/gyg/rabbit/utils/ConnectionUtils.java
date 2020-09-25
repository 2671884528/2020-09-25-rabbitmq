package com.gyg.rabbit.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @auther 郭永钢
 * @data 2020/9/24 16:47
 * @desc: 连接工具
 */

public class ConnectionUtils {


    private static ConnectionFactory connF;

    static {
        connF = new ConnectionFactory();
    }

    public static Connection getConnection(){

        //链接工厂
        connF = new ConnectionFactory();
        connF.setHost("101.37.116.241");
        connF.setPort(5672);
        connF.setVirtualHost("/ems");
        connF.setPassword("root");
        connF.setUsername("root");
        try {
            return connF.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Channel channel,Connection connection){
        try {
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
