package com.Microservice.Galleryserver.Config;

import com.Microservice.Galleryserver.Constant.Constant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
    @Bean
    Queue queue(){
        return new Queue(Constant.QUEUE_NAME, true);
    }

    @Bean
    DirectExchange exchange(){
        return new DirectExchange(Constant.EXCHANGE_NAME);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(Constant.ROUTING_KEY);
    }
}
