package com.spring.rabbitMQdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Rabbit config.
 */
@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.queue}")
    private String queueName;

    @Value("${spring.rabbitmq.exchange}")
    private String exchangeName;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;

    /**
     * Queue queue.
     *
     * @return the queue
     */
    @Bean
    Queue queue() {
        return new Queue(queueName,true);
    }

    /**
     * Exchange topic exchange.
     *
     * @return the topic exchange
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    /**
     * Binding binding.
     *
     * @param queue    the queue
     * @param exchange the exchange
     * @return the binding
     */
    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    /**
     * Rabbit template rabbit template.
     *
     * @param connectionFactory the connection factory
     * @return the rabbit template
     */
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    /**
     * Producer jackson 2 message converter jackson 2 json message converter.
     *
     * @return the jackson 2 json message converter
     */
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
