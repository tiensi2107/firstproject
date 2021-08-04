package com.ntsi.messageprocessor.service.timeseries;

import model.db.TimeSeriesDataitem;
import model.dto.TrackerMessage;

import java.util.List;

public interface TimeSeriesListener {
    void onInsertMultiple(List<TimeSeriesDataitem> items, TrackerMessage trackerMassage);
}
