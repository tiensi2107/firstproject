package com.ntsi.gpxgateway.configuration;

import com.ntsi.gpxgateway.rabbitmq.ExchangeName;
import com.ntsi.gpxgateway.rabbitmq.QueueName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {
    private static final Logger logger = LogManager.getLogger(RabbitMQConfig.class);

    public static final int TTL = 300000;

    //Defining Queue
    @Bean
    Queue RABBITMQ_QUEUE_GPX_REQUEST_MAIN() {
        logger.info("Creating queue {}", QueueName.RABBITMQ_QUEUE_GPX_REQUEST_MAIN);
        return QueueBuilder.durable(QueueName.RABBITMQ_QUEUE_GPX_REQUEST_MAIN)
                .deadLetterExchange(ExchangeName.RABBITMQ_EXCHANGE_DIRECT_GPX_PARKING)
                .deadLetterRoutingKey(QueueName.RABBITMQ_QUEUE_GPX_REQUEST_PARKING)
                .ttl(TTL)
                .build();
    }

    @Bean
    Queue RABBITMQ_QUEUE_GPX_REQUEST_RETRY() {
        logger.info("Creating queue {}", QueueName.RABBITMQ_QUEUE_GPX_REQUEST_RETRY);
        return QueueBuilder.durable(QueueName.RABBITMQ_QUEUE_GPX_REQUEST_RETRY)
                .deadLetterExchange(ExchangeName.RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN)
                .deadLetterRoutingKey(QueueName.RABBITMQ_QUEUE_GPX_REQUEST_MAIN)
                .ttl(TTL)
                .build();
    }

    @Bean
    Queue RABBITMQ_QUEUE_GPX_REQUEST_PARKING() {
        logger.info("Creating queue {}", QueueName.RABBITMQ_QUEUE_GPX_REQUEST_PARKING);
        return new Queue(QueueName.RABBITMQ_QUEUE_GPX_REQUEST_PARKING, true);
    }

    //Defining Exchange
    @Bean
    DirectExchange RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN() {
        logger.info("Creating exchange {}", ExchangeName.RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN);
        return new DirectExchange(ExchangeName.RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN);
    }
    @Bean
    DirectExchange RABBITMQ_EXCHANGE_DIRECT_REQUEST_RETRY() {
        logger.info("Creating exchange {}", ExchangeName.RABBITMQ_EXCHANGE_DIRECT_REQUEST_RETRY);
        return new DirectExchange(ExchangeName.RABBITMQ_EXCHANGE_DIRECT_REQUEST_RETRY);
    }
    @Bean
    DirectExchange RABBITMQ_EXCHANGE_DIRECT_GPX_PARKING(){
        logger.info("Creating exchange {}",ExchangeName.RABBITMQ_EXCHANGE_DIRECT_GPX_PARKING);
        return new DirectExchange(ExchangeName.RABBITMQ_EXCHANGE_DIRECT_GPX_PARKING);
    }
    //Start Binding
    @Bean
    Binding RABBITMQ_QUEUE_GPX_REQUEST_MAIN_BINDING(Queue RABBITMQ_QUEUE_GPX_REQUEST_MAIN,DirectExchange RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN){
        logger.info("Binding queue RABBITMQ_QUEUE_GPX_REQUEST_MAIN to exchange RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN");
        return BindingBuilder.bind(RABBITMQ_QUEUE_GPX_REQUEST_MAIN)
                .to(RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN)
                .with(QueueName.RABBITMQ_QUEUE_GPX_REQUEST_MAIN);
    }
    @Bean
    Binding RABBITMQ_QUEUE_GPX_REQUEST_RETRY_BINDING(Queue RABBITMQ_QUEUE_GPX_REQUEST_RETRY, DirectExchange RABBITMQ_EXCHANGE_DIRECT_REQUEST_RETRY){
        logger.info("Binding queue RABBITMQ_QUEUE_GPX_REQUEST_RETRY to RABBITMQ_EXCHANGE_DIRECT_REQUEST_RETRY");
        return  BindingBuilder.bind(RABBITMQ_QUEUE_GPX_REQUEST_RETRY)
                .to(RABBITMQ_EXCHANGE_DIRECT_REQUEST_RETRY)
                .with(QueueName.RABBITMQ_QUEUE_GPX_REQUEST_RETRY);
    }
    @Bean
    Binding RABBITMQ_QUEUE_GPX_REQUEST_PARKING_BINDING(Queue RABBITMQ_QUEUE_GPX_REQUEST_PARKING,DirectExchange RABBITMQ_EXCHANGE_DIRECT_GPX_PARKING){
        logger.info("Binding queue RABBITMQ_QUEUE_GPX_REQUEST_PARKING to RABBITMQ_EXCHANGE_DIRECT_GPX_PARKING");
        return BindingBuilder.bind(RABBITMQ_QUEUE_GPX_REQUEST_PARKING)
                .to(RABBITMQ_EXCHANGE_DIRECT_GPX_PARKING)
                .with(QueueName.RABBITMQ_QUEUE_GPX_REQUEST_PARKING);
    }
}

