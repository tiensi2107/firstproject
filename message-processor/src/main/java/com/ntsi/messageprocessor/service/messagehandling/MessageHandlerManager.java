package com.ntsi.messageprocessor.service.messagehandling;

import model.dto.TrackerMessage;
import model.dto.TrackerMessageData;
import model.dto.TrackerMessageDataType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MessageHandlerManager {
    private final Map<String, TrackerMetadataHandlingDecorator> trackerMetadataHandlingDecorators;

    public MessageHandlerManager(Map<String,TrackerMetadataHandlingDecorator> trackerMetadataHandlingDecorators) {
        this.trackerMetadataHandlingDecorators = trackerMetadataHandlingDecorators;
    }


    public void handle(TrackerMessage trackerMassage){
        TrackerMessageData trackerMessageData = trackerMassage.getTrackerMessageData();
        List<MessageHandler> handlers = getHandler(trackerMessageData.getMessageDataType());
        handlers.forEach(messageHandler -> {
            messageHandler.handle(trackerMassage);
        });
    }
    private List<MessageHandler> getHandler(TrackerMessageDataType trackerDataType){
        List<MessageHandler> handlers = new ArrayList<>();

        String beanDef = TrackerMessageTypeFactory.getBeanDefinitionStr(trackerDataType);
        if (beanDef != null){
            TrackerMetadataHandlingDecorator trackerMetadataHandlingDecorator = trackerMetadataHandlingDecorators.get(beanDef);
            if(trackerMetadataHandlingDecorator != null){
                handlers.add(trackerMetadataHandlingDecorator);
            }
        }
        return handlers;
    }
}
