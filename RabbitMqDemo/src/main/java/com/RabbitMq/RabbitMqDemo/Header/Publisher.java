package com.RabbitMq.RabbitMqDemo.Header;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String message = "Message for Mobile and TV";
        Map<String, Object> headers = new HashMap<>();
        headers.put("item1", "mobile");
        headers.put("item2", "television");
        AMQP.BasicProperties properties = new AMQP.BasicProperties()
                .builder()
                .headers(headers)
                .build();
        channel.basicPublish("Header-Exchange", "", properties, message.getBytes(StandardCharsets.UTF_8));

        channel.close();
        connection.close();
    }
}
