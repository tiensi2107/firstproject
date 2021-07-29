package com.ntsi.messageprocessor.service.messagehandling;

import com.ntsi.messageprocessor.model.dto.TrackerMassage;

public abstract class TrackerHandlerDecorator implements MessageHandler{
    private MessageHandler decoratedHandler;

    public void setDecoratedHandler(MessageHandler decoratedHandler) {
        this.decoratedHandler = decoratedHandler;
    }

    @Override
    public void handle(TrackerMassage trackerMassage) {
        decoratedHandler.handle(trackerMassage);
    }
}
