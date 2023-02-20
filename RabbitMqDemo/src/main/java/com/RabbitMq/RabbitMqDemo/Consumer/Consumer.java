package com.RabbitMq.RabbitMqDemo.Consumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // DeliverCallBack là một interface của thư viện AMQP để xử lý tin nhắn được gửi tới consumer
        DeliverCallback deliverCallback = (consumerTag, delivery)->{
          String message = new String(delivery.getBody());
          System.out.println("Message received: " + message);
        };
        channel.basicConsume("Queue-1", true, deliverCallback, consumerTag ->{});
    }
}
