package com.ntsi.messageprocessor.service.messagehandling;

import com.ntsi.messageprocessor.model.dto.TrackerDataType;
import com.ntsi.messageprocessor.model.dto.TrackerMassage;
import com.ntsi.messageprocessor.model.dto.TrackerMessageData;
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


    public void handle(TrackerMassage trackerMassage){
        TrackerMessageData trackerMessageData = trackerMassage.getTrackerMessageData();
        List<MessageHandler> handlers = getHandler(trackerMessageData.getMessageType());
        handlers.forEach(messageHandler -> {
            messageHandler.handle(trackerMassage);
        });
    }
    private List<MessageHandler> getHandler(TrackerDataType trackerDataType){
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
