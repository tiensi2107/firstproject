package com.ntsi.messageprocessor.service.timeseries.impl;

import com.ntsi.messageprocessor.config.rabbitmq.RabbitMQConfiguration;
import com.ntsi.messageprocessor.service.timeseries.TimeSeriesListener;
import com.ntsi.messageprocessor.service.timeseries.TimeseriesService;
import com.ntsi.messageprocessor.model.db.TimeSeriesDataitem;
import com.ntsi.messageprocessor.model.dto.ArchivingTimeSeriesObject;
import com.ntsi.messageprocessor.model.dto.TrackerMassage;
import com.ntsi.messageprocessor.model.rabbitmq.QueueName;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeSeriesArchivingListener implements TimeSeriesListener {

    private static Logger log = LogManager.getLogger(RabbitMQConfiguration.class);

    private final TimeseriesService timeseriesService;

    private final AmqpTemplate amqpTemplate;


    public TimeSeriesArchivingListener(TimeseriesService timeseriesService, AmqpTemplate amqpTemplate) {
        this.timeseriesService = timeseriesService;
        this.amqpTemplate = amqpTemplate;
    }
    @PostConstruct
    public void init(){
        timeseriesService.registerListener(this);
    }

    @Override
    public void onInsertMultiple(List<TimeSeriesDataitem> items, TrackerMassage trackerMassage) {
        if (CollectionUtils.isNotEmpty(items)){
            List<TimeSeriesDataitem> validtimeSeriesDataItems = new ArrayList<>();
            for (TimeSeriesDataitem timeSeriesDataitem : items){
                if (valid(timeSeriesDataitem)){
                    validtimeSeriesDataItems.add(timeSeriesDataitem);
                }else {
                    log.warn("Restricted a time series item because it was invalid {}", timeSeriesDataitem);
                }
            }
            ArchivingTimeSeriesObject archivingTimeSeriesObject = new ArchivingTimeSeriesObject(validtimeSeriesDataItems);
            amqpTemplate.convertAndSend(QueueName.TIMESERIES_MESSAGE_MAIN, archivingTimeSeriesObject);
        }
    }
    private boolean valid(TimeSeriesDataitem timeSeriesDataitem){
        if (StringUtils.isNotEmpty(timeSeriesDataitem.getValue())){
            return false;
        }
        if (timeSeriesDataitem.getMetricType() == null){
            return false;
        }
        if (StringUtils.isNotEmpty(timeSeriesDataitem.getTrackerDataId())){
            return false;
        }
        if (StringUtils.isNotEmpty(timeSeriesDataitem.getTrackerID())){
            return false;
        }
        if (timeSeriesDataitem.getTimestamp() == null){
            return false;
        }
        return true;
    }
}
