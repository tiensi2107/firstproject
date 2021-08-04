package model.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TrackerMessageInfo {
    private String imei;

    private TrackerMessageData trackerMessageData;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public TrackerMessageData getTrackerMessageData() {
        return trackerMessageData;
    }

    public void setTrackerMessageData(TrackerMessageData trackerMessageData) {
        this.trackerMessageData = trackerMessageData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("imei", imei)
                .append("trackerMessageData", trackerMessageData)
                .toString();
    }
}
