package com.ntsi.messageprocessor.service.timeseries;

import model.db.TimeSeriesDataitem;
import model.dto.TrackerMassage;
import org.apache.catalina.LifecycleState;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class TimeseriesService {
    private Set<TimeSeriesListener> timeSeriesListeners = new LinkedHashSet<>();

    public void insertMultiple(List<TimeSeriesDataitem> items, TrackerMassage trackerMassage){
        if (CollectionUtils.isNotEmpty(items)){
            for (TimeSeriesListener listener : timeSeriesListeners){
                listener.onInsertMultiple(items, trackerMassage);
            }
        }
    }
    public void insert(TimeSeriesDataitem item, TrackerMassage trackerMassage){
        insertMultiple(Collections.singletonList(item), trackerMassage);
    }
    public void registerListener(TimeSeriesListener listener){
        timeSeriesListeners.add(listener);
    }
}
