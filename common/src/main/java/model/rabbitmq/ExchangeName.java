package model.rabbitmq;

public interface ExchangeName {
    String RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN = "gpx.request.main";
    String RABBITMQ_EXCHANGE_DIRECT_REQUEST_RETRY = "gpx.request.retry";
    String RABBITMQ_EXCHANGE_DIRECT_GPX_REQUEST_PARKING = "gpx.request.parking";
}
