package com.ntsi.messageprocessor.service.timeseries.impl;

import com.ntsi.messageprocessor.service.timeseries.TimeSeriesListener;
import com.ntsi.messageprocessor.service.timeseries.TimeseriesService;
import model.db.MetricType;
import model.db.TimeSeriesDataitem;
import model.dto.GeoTimeSeriesObject;
import model.dto.TrackerMassage;
import model.rabbitmq.QueueName;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class GeoDecoderListener implements TimeSeriesListener {
    private final TimeseriesService timeseriesService;

    private final AmqpTemplate amqpTemplate;


    public GeoDecoderListener(TimeseriesService timeseriesService, AmqpTemplate amqpTemplate) {
        this.timeseriesService = timeseriesService;
        this.amqpTemplate = amqpTemplate;
    }
    @PostConstruct
    public void init(){
        timeseriesService.registerListener(this);
    }

    @Override
    public void onInsertMultiple(List<TimeSeriesDataitem> items, TrackerMassage trackerMassage) {
        TimeSeriesDataitem locationItem = getLocationItem(items);
        if (locationItem != null) {
            GeoTimeSeriesObject geoTimeSeriesObject = new GeoTimeSeriesObject(locationItem);
            amqpTemplate.convertAndSend(QueueName.GEODECODER_MESSAGE_MAIN, geoTimeSeriesObject);
        }
    }
    private TimeSeriesDataitem getLocationItem(List<TimeSeriesDataitem> items){
        for (TimeSeriesDataitem timeSeriesDataitem : items){
            if(MetricType.TRACKER_DATA_GPS_LOCATION_LAT_LNG.equals(timeSeriesDataitem.getMetricType()) && timeSeriesDataitem.getValue() != null){
                return timeSeriesDataitem;
            }
        }
        return null;
    }
}
