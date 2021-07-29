package com.ntsi.messageprocessor.service.messagehandling;

import com.ntsi.messageprocessor.model.dto.TrackerMassage;

public interface MessageHandler {
    void handle(TrackerMassage trackerMassage);
}
