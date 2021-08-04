package com.ntsi.messageprocessor.service.timeseries.impl;

import com.ntsi.messageprocessor.service.timeseries.TimeSeriesListener;
import com.ntsi.messageprocessor.service.timeseries.TimeSeriesService;
import model.db.TimeSeriesDataitem;
import model.dto.TrackerMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class AlertValidatorListener implements TimeSeriesListener {
    private final TimeSeriesService timeseriesService;


    public AlertValidatorListener(TimeSeriesService timeseriesService) {
        this.timeseriesService = timeseriesService;

    }
    @PostConstruct
    public void init(){
        timeseriesService.registerListener(this);
    }

    @Override
    public void onInsertMultiple(List<TimeSeriesDataitem> items, TrackerMessage trackerMassage) {

    }
}
