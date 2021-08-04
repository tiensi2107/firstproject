package com.ntsi.gpxgateway.config;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import util.RabbitMQMessageConverter;

@Component
public class RabbitQueueConfiguration {
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new RabbitMQMessageConverter();
    }
}
