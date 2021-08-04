package com.ntsi.messageprocessor.service.messagehandling;

import model.dto.TrackerMessageDataType;

public class TrackerMessageTypeFactory {
    public static final String GPX_METADATA_DECORATOR = "gpxMetadata";

    public static String getBeanDefinitionStr(TrackerMessageDataType trackerDataType){
        switch (trackerDataType){
            case GPX:
                return GPX_METADATA_DECORATOR;
            default:
                throw new RuntimeException("Unsupport tracker data type" + trackerDataType);
        }
    }
}
