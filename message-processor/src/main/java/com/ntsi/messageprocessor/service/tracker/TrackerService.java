package com.ntsi.messageprocessor.service.tracker;

import model.db.Tracker;

public interface TrackerService {
    Tracker findByImei(String imei);
}
