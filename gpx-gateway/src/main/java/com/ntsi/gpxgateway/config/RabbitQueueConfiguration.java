package com.ntsi.gpxgateway.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import util.RabbitMQMessageConverter;

@Configuration
public class RabbitQueueConfiguration {
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new RabbitMQMessageConverter();
    }
}
