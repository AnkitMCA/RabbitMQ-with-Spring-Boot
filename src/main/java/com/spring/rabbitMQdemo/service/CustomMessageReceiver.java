package com.spring.rabbitMQdemo.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * The type Custom message receiver.
 */
@Service
public class CustomMessageReceiver {

    private final RabbitTemplate rabbitTemplate;

    /**
     * Instantiates a new Custom message receiver.
     *
     * @param rabbitTemplate the rabbit template
     */
    public CustomMessageReceiver(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /*
    @RabbitListener(queues = "rabbitmq-queue")
    public void receiveMessage(Object message){
        System.out.println(".............................Object :  "+message);
    }
    */


    /**
     * Receive message.
     *
     * @param queueName the queue name
     */
    public void receiveMessages(String queueName) {
        Object object = rabbitTemplate.receiveAndConvert(queueName);
        String msg = (String) object;
        System.out.println("Received : " + msg);
    }
}