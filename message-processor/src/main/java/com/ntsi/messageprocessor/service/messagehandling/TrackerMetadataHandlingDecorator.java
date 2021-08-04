package com.ntsi.messageprocessor.service.messagehandling;

import model.dto.TrackerMessage;
import org.springframework.stereotype.Component;

@Component
public class TrackerMetadataHandlingDecorator extends TrackerHandlerDecorator{
    @Override
    public void handle(TrackerMessage trackerMessage) {
        super.handle(trackerMessage);
    }
}
