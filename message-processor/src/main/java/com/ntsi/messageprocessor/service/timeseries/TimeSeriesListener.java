package com.ntsi.messageprocessor.service.timeseries;

import model.db.TimeSeriesDataitem;
import model.dto.TrackerMassage;

import java.util.List;

public interface TimeSeriesListener {
    void onInsertMultiple(List<TimeSeriesDataitem> items, TrackerMassage trackerMassage);
}
