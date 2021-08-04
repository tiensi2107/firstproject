package com.ntsi.messageprocessor.service.messagehandling;

import model.dto.TrackerMessage;

public abstract class TrackerHandlerDecorator implements MessageHandler{
    private MessageHandler decoratedHandler;

    public void setDecoratedHandler(MessageHandler decoratedHandler) {
        this.decoratedHandler = decoratedHandler;
    }

    @Override
    public void handle(TrackerMessage trackerMessage) {
        decoratedHandler.handle(trackerMessage);
    }

}
