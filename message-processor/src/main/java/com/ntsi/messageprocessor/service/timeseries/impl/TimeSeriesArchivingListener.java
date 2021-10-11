package com.ntsi.messageprocessor.service.timeseries.impl;

import com.ntsi.messageprocessor.config.RabbitMQConfiguration;
import com.ntsi.messageprocessor.service.timeseries.TimeSeriesListener;
import com.ntsi.messageprocessor.service.timeseries.TimeSeriesService;
import model.db.TimeSeriesDataitem;
import model.dto.ArchivingTimeSeriesObject;
import model.dto.TrackerMessage;
import model.rabbitmq.QueueName;
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

    private final TimeSeriesService timeSeriesService;
    private final AmqpTemplate amqpTemplate;

    public TimeSeriesArchivingListener(TimeSeriesService timeSeriesService, AmqpTemplate amqpTemplate) {
        this.timeSeriesService = timeSeriesService;
        this.amqpTemplate = amqpTemplate;
    }
    @PostConstruct
    public void init(){
        timeSeriesService.registerListener(this);
    }

    @Override
    public void onInsertMultiple(List<TimeSeriesDataitem> items, TrackerMessage trackerMassage) {
        if(CollectionUtils.isNotEmpty(items)){
            List<TimeSeriesDataitem> validTimeSeriesDataItems = new ArrayList<>();
            for (TimeSeriesDataitem timeSeriesDataitem : items){
                if(valid(timeSeriesDataitem)){
                    validTimeSeriesDataItems.add(timeSeriesDataitem);
                }else {
                    log.warn("Restricted a time series item because it was invalid {}", timeSeriesDataitem);
                }
            }
            ArchivingTimeSeriesObject archivingTimeSeriesObject = new ArchivingTimeSeriesObject(validTimeSeriesDataItems);
            amqpTemplate.convertAndSend(QueueName.TIMESERIES_MESSAGE_MAIN, archivingTimeSeriesObject);
        }
    }
    private boolean valid(TimeSeriesDataitem timeSeriesDataitem) {
        if (StringUtils.isEmpty(timeSeriesDataitem.getValue())) {
            return false;
        }

        if (timeSeriesDataitem.getMetricType() == null) {
            return false;
        }

        if (StringUtils.isEmpty(timeSeriesDataitem.getTrackerDataId())) {
            return false;
        }

        if (StringUtils.isEmpty(timeSeriesDataitem.getTrackerID())) {
            return false;
        }

        if (timeSeriesDataitem.getTimestamp() == null) {
            return false;
        }

        return true;
    }
}
