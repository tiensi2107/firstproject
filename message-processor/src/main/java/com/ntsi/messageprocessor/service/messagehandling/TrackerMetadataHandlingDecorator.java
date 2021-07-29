package com.ntsi.messageprocessor.service.messagehandling;

import model.dto.TrackerMassage;
import org.springframework.stereotype.Component;
@Component
public class TrackerMetadataHandlingDecorator extends TrackerHandlerDecorator{
    @Override
    public void handle(TrackerMassage trackerMassage) {
        super.handle(trackerMassage);
    }
}
