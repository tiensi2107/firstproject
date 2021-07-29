package com.ntsi.messageprocessor.service.tracker;

import com.ntsi.messageprocessor.model.db.Tracker;

public interface TrackerService {
    Tracker findByImei(String imei);
}
