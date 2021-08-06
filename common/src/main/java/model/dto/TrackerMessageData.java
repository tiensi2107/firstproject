package model.dto;

import model.db.GpxMessageData;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;

public abstract class TrackerMessageData {
    public static final String MESSAGE_TYPE = "messageDataType";
    private String trackerDataID;

    private LocalDateTime timestamp;

    private TrackerMessageDataType messageDataType;

    public TrackerMessageData(TrackerMessageDataType messageDataType){
        this.messageDataType = messageDataType;
    }


    public static TrackerMessageData getTrackerMessageData(TrackerMessageDataType type, String jsonData) throws Exception{
        switch (type){
            case GPX:
                return GpxMessageData.fromJson(jsonData);
            default:
                throw new Exception("Message Data Type"+ type +"don't supported");
        }
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

    public TrackerMessageDataType getMessageDataType() {
        return messageDataType;
    }

    public void setMessageDataType(TrackerMessageDataType messageDataType) {
        this.messageDataType = messageDataType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("trackerDataId", trackerDataID)
                .append("timestamp", timestamp)
                .append("messageDataType", messageDataType)
                .toString();
    }
}
