package model.dto;

import model.dto.TrackerDataType;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

public abstract class TrackerMessageData {
    private String trackerDataID;

    private LocalDateTime timestamp;

    private TrackerDataType messageType;

    public TrackerMessageData(TrackerDataType messageType){
        this.messageType = messageType;
    }

    public String getTrackerDataID() {
        return trackerDataID;
    }

    public void setTrackerDataID(String trackerDataID) {
        this.trackerDataID = trackerDataID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public TrackerDataType getMessageType() {
        return messageType;
    }

    public void setMessageType(TrackerDataType messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("trackerDataId", trackerDataID)
                .append("timestamp", timestamp)
                .append("messageType", messageType)
                .toString();
    }
}
