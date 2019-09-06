package com.spring.rabbitMQdemo.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * The type Custom message sender.
 */
@Service
public class CustomMessageSender {

    private final RabbitTemplate rabbitTemplate;

    /**
     * Instantiates a new Custom message sender.
     *
     * @param rabbitTemplate the rabbit template
     */
    public CustomMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Send message.
     *
     * @param exchange   the exchange
     * @param routingKey the routing key
     * @param data       the data
     */
    public void sendMessage(String exchange, String routingKey, Object data) {
        System.out.println("Sending message. : " + data);
        rabbitTemplate.convertAndSend(exchange, routingKey, data);
    }
}
