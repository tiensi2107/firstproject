package model.rabbitmq;

public interface QueueName {
    String RABBITMQ_QUEUE_GPX_REQUEST_MAIN = "gpx.request.main";
    String RABBITMQ_QUEUE_GPX_REQUEST_RETRY = "gpx.request.retry";
    String RABBITMQ_QUEUE_GPX_REQUEST_PARKING = "gpx.request.parking";
}
