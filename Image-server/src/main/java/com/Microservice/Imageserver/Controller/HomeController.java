package com.Microservice.Imageserver.Controller;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.Microservice.Imageserver.Constant.Constant;
import com.Microservice.Imageserver.Entity.Image;
import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HomeController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("images")
    public List<Image> getImages() {
        List<Image> images = Arrays.asList(
                new Image(1, "Treehouse of Horror V", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3842005760"),
                new Image(2, "The Town", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3698134272"),
                new Image(3, "The Last Traction Hero", "https://www.imdb.com/title/tt0096697/mediaviewer/rm1445594112"));
        var mess = rabbitTemplate.receive(Constant.QUEUE_NAME);
        var messInfo = new String(mess.getBody(), StandardCharsets.UTF_8);
        if(!Strings.isNullOrEmpty(messInfo)){
            String jsonMess = new Gson().toJson(images);
            CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
            Message message = MessageBuilder.withBody(jsonMess.getBytes(StandardCharsets.UTF_8))
                    .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN)
                    .setCorrelationId(correlationData.getId())
                    .build();
            rabbitTemplate.convertAndSend(Constant.EXCHANGE_NAME, Constant.ROUTING_KEY, message, correlationData);
        }
        return images;
    }

    @RequestMapping("/string")
    public String getString(){
        return "Nam";
    }
}
