package com.ntsi.messageprocessor.service.timeseries;

import com.ntsi.messageprocessor.model.db.TimeSeriesDataitem;
import com.ntsi.messageprocessor.model.dto.TrackerMassage;

import java.util.List;

public interface TimeSeriesListener {
    void onInsertMultiple(List<TimeSeriesDataitem> items, TrackerMassage trackerMassage);
}
