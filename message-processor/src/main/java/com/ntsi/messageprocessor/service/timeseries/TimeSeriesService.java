package com.ntsi.messageprocessor.service.timeseries;

import model.db.TimeSeriesDataitem;
import model.dto.TrackerMessage;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Service
public class TimeSeriesService {
    private Set<TimeSeriesListener> timeSeriesListeners = new LinkedHashSet<>();

    public void insertMultiple(List<TimeSeriesDataitem> items, TrackerMessage trackerMassage){
        if (CollectionUtils.isNotEmpty(items)){
            for (TimeSeriesListener listener : timeSeriesListeners){
                listener.onInsertMultiple(items, trackerMassage);
            }
        }
    }
    public void insert(TimeSeriesDataitem item, TrackerMessage trackerMassage){
        insertMultiple(Collections.singletonList(item), trackerMassage);
    }
    public void registerListener(TimeSeriesListener listener){
        timeSeriesListeners.add(listener);
    }
}
