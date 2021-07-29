package model.dto;

import model.db.Tracker;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TrackerMassage {
    private Tracker tracker;

    private TrackerMessageData trackerMessageData;

    public TrackerMassage(Tracker tracker, TrackerMessageData trackerMessageData) {
        this.tracker = tracker;
        this.trackerMessageData = trackerMessageData;
    }
    public TrackerMassage(){}

    public Tracker getTracker() {
        return tracker;
    }

    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
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
                .append("tracker", tracker)
                .append("trackerMessageType", trackerMessageData)
                .toString();
    }
}
