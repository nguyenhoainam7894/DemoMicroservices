package com.RabbitMq.RabbitMqDemo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMqDemoApplication {

	public static void main(String[] args) throws IOException, TimeoutException {
		SpringApplication.run(RabbitMqDemoApplication.class, args);
	}

}
