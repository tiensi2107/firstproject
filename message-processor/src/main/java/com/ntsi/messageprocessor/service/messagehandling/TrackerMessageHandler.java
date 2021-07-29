package com.ntsi.messageprocessor.service.messagehandling;

import com.ntsi.messageprocessor.service.timeseries.TimeseriesService;
import model.db.MetricType;
import model.db.TimeSeriesDataitem;
import model.db.Tracker;
import model.dto.TrackerMassage;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class TrackerMessageHandler implements MessageHandler{

    @Autowired
    TimeseriesService timeseriesService;

    protected void insertMultiple(Map<MetricType, String> values, LocalDateTime messageDate, TrackerMassage message){
        List<TimeSeriesDataitem> items = new ArrayList<>();
        Tracker tracker = message.getTracker();
        for (MetricType metricType : values.keySet()){
            String value = values.get(metricType);
            if (value != null){
                TimeSeriesDataitem item = new TimeSeriesDataitem(tracker.getId(), metricType, messageDate, value, message.getTrackerMessageData().getTrackerDataID());
            }
        }
        timeseriesService.insertMultiple(items, message);
    }
}
