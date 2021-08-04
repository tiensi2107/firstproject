package com.ntsi.gpxgateway.config;

import model.rabbitmq.ExchangeName;
import model.rabbitmq.QueueName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private static Logger log = LogManager.getLogger(RabbitMQConfig.class);

    public static final int TTL = 300000;

    //DEFINING QUEUES

    @Bean
    Queue RABBITMQ_QUEUE_GPX_REQUEST_MAIN() {
        log.info("Creating queue {}", QueueName.RABBITMQ_QUEUE_GPX_REQUEST_MAIN);
        return QueueBuilder.durable(QueueName.RABBITMQ_QUEUE_GPX_REQUEST_MAIN)
                .deadLetterExchange(ExchangeName.RABBITMQ_EXCHANGE_DIRECT_GPX_REQUEST_PARKING)
                .deadLetterRoutingKey(QueueName.RABBITMQ_QUEUE_GPX_REQUEST_PARKING)
                .ttl(TTL)
                .build();
    }

    @Bean
    Queue RABBITMQ_QUEUE_GPX_REQUEST_RETRY() {
        log.info("Creating queue {}", QueueName.RABBITMQ_QUEUE_GPX_REQUEST_RETRY);
        return QueueBuilder.durable(QueueName.RABBITMQ_QUEUE_GPX_REQUEST_RETRY)
                .deadLetterExchange(ExchangeName.RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN)
                .deadLetterRoutingKey(QueueName.RABBITMQ_QUEUE_GPX_REQUEST_MAIN)
                .ttl(TTL)
                .build();
    }

    @Bean
    Queue RABBITMQ_QUEUE_GPX_REQUEST_PARKING() {
        log.info("Creating queue {}", QueueName.RABBITMQ_QUEUE_GPX_REQUEST_PARKING);
        return new Queue(QueueName.RABBITMQ_QUEUE_GPX_REQUEST_PARKING, true);
    }

    //DEFINING EXCHANGES

    @Bean
    DirectExchange RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN() {
        log.info("Creating exchange {}", ExchangeName.RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN);
        return new DirectExchange(ExchangeName.RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN);
    }

    @Bean
    DirectExchange RABBITMQ_EXCHANGE_DIRECT_REQUEST_RETRY() {
        log.info("Creating exchange {}", ExchangeName.RABBITMQ_EXCHANGE_DIRECT_REQUEST_RETRY);
        return new DirectExchange(ExchangeName.RABBITMQ_EXCHANGE_DIRECT_REQUEST_RETRY);
    }

    @Bean
    DirectExchange RABBITMQ_EXCHANGE_DIRECT_GPX_REQUEST_PARKING() {
        log.info("Creating exchange {}", ExchangeName.RABBITMQ_EXCHANGE_DIRECT_GPX_REQUEST_PARKING);
        return new DirectExchange(ExchangeName.RABBITMQ_EXCHANGE_DIRECT_GPX_REQUEST_PARKING);
    }

    //START BIDING

    @Bean
    Binding RABBITMQ_QUEUE_GPX_REQUEST_MAIN_BIDING(Queue RABBITMQ_QUEUE_GPX_REQUEST_MAIN, DirectExchange RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN) {
        log.info("Binding queue RABBITMQ_QUEUE_GPX_REQUEST_MAIN to RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN");
        return BindingBuilder.bind(RABBITMQ_QUEUE_GPX_REQUEST_MAIN)
                .to(RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN)
                .with(QueueName.RABBITMQ_QUEUE_GPX_REQUEST_MAIN);
    }

    @Bean
    Binding RABBITMQ_QUEUE_GPX_REQUEST_RETRY_BIDING(Queue RABBITMQ_QUEUE_GPX_REQUEST_RETRY, DirectExchange RABBITMQ_EXCHANGE_DIRECT_REQUEST_RETRY) {
        log.info("Binding queue RABBITMQ_QUEUE_GPX_REQUEST_RETRY to RABBITMQ_EXCHANGE_DIRECT_REQUEST_RETRY");
        return BindingBuilder.bind(RABBITMQ_QUEUE_GPX_REQUEST_RETRY).to(RABBITMQ_EXCHANGE_DIRECT_REQUEST_RETRY).with(QueueName.RABBITMQ_QUEUE_GPX_REQUEST_RETRY);
    }

    @Bean
    Binding RABBITMQ_QUEUE_GPX_REQUEST_PARKING_BIDING(Queue RABBITMQ_QUEUE_GPX_REQUEST_PARKING, DirectExchange RABBITMQ_EXCHANGE_DIRECT_GPX_REQUEST_PARKING) {
        log.info("Binding queue RABBITMQ_QUEUE_GPX_REQUEST_PARKING to RABBITMQ_EXCHANGE_DIRECT_GPX_REQUEST_PARKING");
        return BindingBuilder.bind(RABBITMQ_QUEUE_GPX_REQUEST_PARKING).to(RABBITMQ_EXCHANGE_DIRECT_GPX_REQUEST_PARKING).with(QueueName.RABBITMQ_QUEUE_GPX_REQUEST_PARKING);
    }
}
