package com.ntsi.messageprocessor.config;

import model.rabbitmq.QueueName;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Configuration
@Order
public class RabbitMQProvision {
    public static final String DEADLETTER_SUFFIX = ".deadletter";
    public static final String PARKINGLOT_SUFFIX = ".parkinglot";

    @Bean
    public Declarables basicQueuesAndParkingLots() {
        List<Declarable> declarables = new ArrayList<>();
        declarables.addAll(basicQueueAndParkingLot(QueueName.TRACKER_MESSAGE_MAIN));
        declarables.addAll(basicQueueAndParkingLot(QueueName.TIMESERIES_MESSAGE_MAIN));
        return new Declarables(declarables.toArray(new Declarable[0]));
    }

    private Collection<? extends Declarable> basicQueueAndParkingLot(String queueName) {
        FanoutExchange fanout = new FanoutExchange(queueName + DEADLETTER_SUFFIX);
        Queue mainQueue = QueueBuilder.durable(queueName)
                .withArgument("x-dead-letter-exchange", queueName + DEADLETTER_SUFFIX).build();
        Queue parkingLot = QueueBuilder.durable(queueName + PARKINGLOT_SUFFIX).build();
        return Arrays.asList(fanout, mainQueue, parkingLot, BindingBuilder.bind(parkingLot).to(fanout));
    }
}
