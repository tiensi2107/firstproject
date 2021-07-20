package com.ntsi.gpxgateway.configuration;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitQueueConfiguration {
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new RabbitMQMessageConverter();
    }
}
