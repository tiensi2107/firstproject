package model.db;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


public class TimeSeriesDataitem {

   private String trackerID;

    private MetricType metricType;

    private LocalDateTime timestamp;

    private LocalDateTime insertedTime = LocalDateTime.now(ZoneOffset.UTC);

    private String value;

    private String trackerDataId;

    public TimeSeriesDataitem(){}

    public TimeSeriesDataitem( String trackerID, MetricType metricType, LocalDateTime timestamp, String value, String trackerDataId) {
        this.trackerID = trackerID;
        this.metricType = metricType;
        this.timestamp = timestamp;
        this.value = value;
        this.trackerDataId = trackerDataId;
    }

    public String getTrackerID() {
        return trackerID;
    }

    public void setTrackerID(String trackerID) {
        this.trackerID = trackerID;
    }

    public MetricType getMetricType() {
        return metricType;
    }

    public void setMetricType(MetricType metricType) {
        this.metricType = metricType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getInsertedTime() {
        return insertedTime;
    }

    public void setInsertedTime(LocalDateTime insertedTime) {
        this.insertedTime = insertedTime;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTrackerDataId() {
        return trackerDataId;
    }

    public void setTrackerDataId(String trackerDataId) {
        this.trackerDataId = trackerDataId;
    }
}
