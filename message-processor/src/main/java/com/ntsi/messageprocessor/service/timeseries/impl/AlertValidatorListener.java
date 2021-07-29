package com.ntsi.messageprocessor.service.timeseries.impl;

import com.ntsi.messageprocessor.service.timeseries.TimeSeriesListener;
import com.ntsi.messageprocessor.service.timeseries.TimeseriesService;
import model.db.TimeSeriesDataitem;
import model.dto.TrackerMassage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class AlertValidatorListener implements TimeSeriesListener {
    private final TimeseriesService timeseriesService;


    public AlertValidatorListener(TimeseriesService timeseriesService) {
        this.timeseriesService = timeseriesService;

    }
    @PostConstruct
    public void init(){
        timeseriesService.registerListener(this);
    }

    @Override
    public void onInsertMultiple(List<TimeSeriesDataitem> items, TrackerMassage trackerMassage) {

    }
}
