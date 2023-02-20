package com.Microservice.Galleryserver.Controller;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.UUID;

import com.Microservice.Galleryserver.Constant.Constant;
import com.Microservice.Galleryserver.Entity.Gallery;
import com.Microservice.Galleryserver.Response.Image;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class HomeController {
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private WebClient webClientBuilder;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @RequestMapping("/{id}")
    public Gallery getGallery(@PathVariable final int id) {
        // create gallery object
        Gallery gallery = new Gallery();
        gallery.setId(id);

        // Use RestTemplate
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory ();
//        restTemplate = new RestTemplate(factory);
//        List<Object> images = restTemplate.getForObject("http://localhost:8200/images", List.class);
        // Use WebFlux
        String mess = "Give me a information";
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        Message message = MessageBuilder.withBody(mess.getBytes(StandardCharsets.UTF_8))
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setCorrelationId(correlationData.getId())
                .build();
        rabbitTemplate.convertAndSend(Constant.EXCHANGE_NAME, Constant.ROUTING_KEY, message, correlationData);
        // Use WebFlux
//        var images = webClientBuilder
//                .get()
//                .uri("http://localhost:8200/api/v1/images")
//                .retrieve()
//                .bodyToFlux(Image.class);
//        var test = images.collectList().block();
        var messBack = rabbitTemplate.receive(Constant.QUEUE_NAME);
        var messInfo = new String(messBack.getBody(), StandardCharsets.UTF_8);
        JsonArray jsonObject = new JsonParser().parse(messInfo).getAsJsonArray();

        return gallery;
    }

    @RequestMapping("test/{id}")
    public String test(@PathVariable String id){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        Message message = MessageBuilder.withBody(id.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                .setCorrelationId(correlationData.getId())
                .build();
        var a = rabbitTemplate.sendAndReceive(Constant.EXCHANGE_NAME, Constant.ROUTING_KEY, message, correlationData);
        return new String(a.getBody(), StandardCharsets.UTF_8);
    }
}
