package com.ntsi.messageprocessor.service.messagehandling;

import model.dto.TrackerMessage;

public interface MessageHandler {
    void handle(TrackerMessage trackerMessage);
}
