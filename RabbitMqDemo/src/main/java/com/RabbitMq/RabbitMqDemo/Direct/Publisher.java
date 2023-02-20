package com.RabbitMq.RabbitMqDemo.Direct;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel= connection.createChannel();

        String messgae = " This is mobile";
        channel.basicPublish("Direct-Exchange", "mobile", null, messgae.getBytes(StandardCharsets.UTF_8));
        channel.close();
        connection.close();
    }
}
