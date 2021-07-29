package com.ntsi.messageprocessor.service.tracker.impl;

import com.ntsi.messageprocessor.service.tracker.TrackerService;
import com.ntsi.messageprocessor.model.db.Tracker;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TrackerServiceImp implements TrackerService {
    @Override
    public Tracker findByImei(String imei) {
        Objects.requireNonNull(imei);
        Tracker tracker = new Tracker();
        tracker.setId(imei);
        return tracker;
    }
}
