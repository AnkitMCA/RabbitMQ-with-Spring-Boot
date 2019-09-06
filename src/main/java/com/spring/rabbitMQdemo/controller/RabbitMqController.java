package com.spring.rabbitMQdemo.controller;

import com.spring.rabbitMQdemo.service.CustomMessageReceiver;
import com.spring.rabbitMQdemo.service.CustomMessageSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Rabbit mq controller.
 */
@RestController
@RequestMapping("/")
public class RabbitMqController {

    private final CustomMessageSender customMessageSender;
    private final CustomMessageReceiver customMessageReceiver;

    /**
     * Instantiates a new Rabbit mq controller.
     *
     * @param customMessageSender   the custom message sender
     * @param customMessageReceiver the custom message receiver
     */
    public RabbitMqController(CustomMessageSender customMessageSender, CustomMessageReceiver customMessageReceiver) {
        this.customMessageSender = customMessageSender;
        this.customMessageReceiver = customMessageReceiver;
    }

    @Value("${spring.rabbitmq.queue}")
    private String queueName;

    @Value("${spring.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;


    /**
     * Send string.
     *
     * @return the string
     */
    @GetMapping("send")
    public String send() {
        customMessageSender.sendMessage(exchangeName, routingKey, "Hi, This is my first message.");
        return "Send Successfully, see message on console";
    }

    /**
     * Receive string.
     *
     * @return the string
     */
    @GetMapping("receive")
    public String receive() {
        customMessageReceiver.receiveMessages(queueName);
        return "Receive Successfully, see message on console";
    }
}
