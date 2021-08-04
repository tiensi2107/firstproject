package com.ntsi.messageprocessor.service.consumer;

import com.ntsi.messageprocessor.service.messagehandling.MessageHandlerManager;
import com.ntsi.messageprocessor.service.tracker.TrackerService;
import model.db.Tracker;
import model.dto.TrackerMessage;
import model.dto.TrackerMessageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.stereotype.Service;

@Service
public class TrackerMessageConsumer {
    private static final Logger log = LogManager.getLogger(TrackerMessageConsumer.class);

    private final TrackerService trackerService;

    private final MessageHandlerManager messageHandlerManager;

    public TrackerMessageConsumer(TrackerService trackerService, MessageHandlerManager messageHandlerManager) {
        this.trackerService = trackerService;
        this.messageHandlerManager = messageHandlerManager;
    }
    public void consume(TrackerMessageInfo trackerMessageInfo){
        try {
            log.info("Starting processing a tracker message info {}", trackerMessageInfo);
            Tracker tracker = trackerService.findByImei(trackerMessageInfo.getImei());
            if (tracker == null){
                log.warn("Could not found tracker Imei {}", trackerMessageInfo.getImei());
                return;
            }
            TrackerMessage trackerMassage = new TrackerMessage(tracker, trackerMessageInfo.getTrackerMessageData());
            messageHandlerManager.handle(trackerMassage);

        }
        catch (Exception e){
            log.error("Exception occurs when handling the message: {}.", trackerMessageInfo, e);
            throw new AmqpRejectAndDontRequeueException("Rejecting the message. " + trackerMessageInfo);
        }
    }
}
