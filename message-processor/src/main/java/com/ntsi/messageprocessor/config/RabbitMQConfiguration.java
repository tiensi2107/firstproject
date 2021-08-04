package com.ntsi.messageprocessor.config;

import com.ntsi.messageprocessor.service.consumer.TrackerMessageConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import util.RabbitMQMessageConverter;

@Configuration
public class RabbitMQConfiguration {
    private static Logger log = LogManager.getLogger(RabbitMQConfiguration.class);

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new RabbitMQMessageConverter();
    }

    @Bean
    MessageListenerAdapter trackerMessageListenerAdapter(TrackerMessageConsumer trackerMessageConsumer) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(trackerMessageConsumer, "consume");
        adapter.setMessageConverter(jsonMessageConverter());
        return adapter;
    }

    @Bean
    SimpleMessageListenerContainer trackerMessageContainer(ConnectionFactory connectionFactory,
                                                           MessageListenerAdapter trackerMessageListenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QueueName.TRACKER_MESSAGE_MAIN);
        container.setMessageListener(trackerMessageListenerAdapter);
        log.info("Listening on {}", QueueName.TRACKER_MESSAGE_MAIN);
        return container;
    }
}
