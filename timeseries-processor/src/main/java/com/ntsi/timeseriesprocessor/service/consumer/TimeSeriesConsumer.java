package com.ntsi.timeseriesprocessor.service.consumer;

import com.ntsi.timeseriesprocessor.service.TimeSeriesService;
import model.dto.ArchivingTimeSeriesObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.stereotype.Service;

@Service
public class TimeSeriesConsumer {
    private static final Logger log = LogManager.getLogger(TimeSeriesConsumer.class);

    private final TimeSeriesService timeSeriesService;

    public TimeSeriesConsumer(TimeSeriesService timeSeriesService) {
        this.timeSeriesService = timeSeriesService;
    }
    public void consume(ArchivingTimeSeriesObject archivingTimeSeriesObject){
        try{
            log.info("Starting processing a time-series data {}", archivingTimeSeriesObject);
            timeSeriesService.save(archivingTimeSeriesObject);
        }catch (Exception e){
            log.error("Exception occurs when handling the time-series: {}.", archivingTimeSeriesObject, e);
            throw new AmqpRejectAndDontRequeueException("Reject the time-series." + archivingTimeSeriesObject);
        }
    }
}
