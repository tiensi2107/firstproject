package com.ntsi.timeseriesprocessor.config.rabbitmq;

import com.ntsi.timeseriesprocessor.service.consumer.TimeSeriesConsumer;
import model.rabbitmq.QueueName;
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
    public MessageConverter jsonMessageConverter(){
        return new RabbitMQMessageConverter();
    }

    @Bean
    MessageListenerAdapter timeSeriesListenerAdapter(TimeSeriesConsumer timeSeriesConsumer){
        MessageListenerAdapter adapter = new MessageListenerAdapter(timeSeriesConsumer, "consume");
        adapter.setMessageConverter(jsonMessageConverter());
        return adapter;
    }
    @Bean
    SimpleMessageListenerContainer trackerMessageContainer(ConnectionFactory connectionFactory, MessageListenerAdapter timeSeriesListenerAdapter){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(QueueName.TIMESERIES_MESSAGE_MAIN);
        container.setMessageListener(timeSeriesListenerAdapter);
        log.info("Listening on {}", QueueName.TIMESERIES_MESSAGE_MAIN);
        return container;
    }
}
