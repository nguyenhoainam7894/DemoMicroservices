package com.RabbitMq.RabbitMqDemo.Publisher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Publisher {
    public static void main(String[] args) throws IOException, TimeoutException {
        // To publish message to queue, We need to use the connection factory.
        ConnectionFactory factory = new ConnectionFactory();
        //  Create object of connection
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // Have 3 parameter (exchange, routing key, props, body)
        //String message = "First message from Rabbit Mq";
        String[] message = {"1", "2", "3", "4"};
        for (String mess : message) {
            channel.basicPublish("", "Queue-1", null, mess.getBytes(StandardCharsets.UTF_8));
        }
        channel.close();
        connection.close();
    }
}
