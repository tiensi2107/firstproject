package com.ntsi.messageprocessor.service.messagehandling;

import model.dto.TrackerMassage;

public interface MessageHandler {
    void handle(TrackerMassage trackerMassage);
}
