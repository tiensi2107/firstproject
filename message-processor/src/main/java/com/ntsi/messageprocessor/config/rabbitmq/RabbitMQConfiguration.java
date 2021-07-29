package com.ntsi.messageprocessor.config.rabbitmq;

import com.ntsi.gpxgateway.configuration.RabbitQueueConfiguration;
import com.ntsi.messageprocessor.service.consumer.TrackerMessageConsumer;
import com.ntsi.messageprocessor.model.rabbitmq.QueueName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfiguration {
    private static final Logger log = LogManager.getLogger(RabbitMQConfiguration.class);
    @Autowired
    RabbitQueueConfiguration rabbitQueueConfiguration;
    @Bean
    MessageListenerAdapter trackerMessageListenerAdaper(TrackerMessageConsumer trackerMessageConsumer){
        MessageListenerAdapter adapter = new MessageListenerAdapter(trackerMessageConsumer, "consume");
        adapter.setMessageConverter(rabbitQueueConfiguration.jsonMessageConverter());
        return adapter;
    }
    @Bean
    SimpleMessageListenerContainer trackeMessageContainer(ConnectionFactory connectionFactory, MessageListenerAdapter trackerMessageListenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QueueName.TRACKER_MESSAGE_MAIN);
        container.setMessageListener(trackerMessageListenerAdapter);
        log.info("Listening on {}", QueueName.TRACKER_MESSAGE_MAIN);
        return container;
    }
}
