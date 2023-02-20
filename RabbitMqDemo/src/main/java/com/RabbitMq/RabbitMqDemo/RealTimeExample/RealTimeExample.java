package com.RabbitMq.RabbitMqDemo.RealTimeExample;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.json.JSONObject;

public class RealTimeExample {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        JSONObject json = new JSONObject();
        json.put("name", "Nam");
        json.put("age", 30);

        channel.basicPublish("", "Queue-1", null, json.toString().getBytes());
        channel.close();
        connection.close();
    }
}
