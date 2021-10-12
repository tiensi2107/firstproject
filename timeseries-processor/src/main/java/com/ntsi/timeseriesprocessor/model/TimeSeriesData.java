package com.ntsi.timeseriesprocessor.model;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import model.db.MetricType;
import model.db.TimeSeriesDataitem;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Measurement(name = "timeseries")
public class TimeSeriesData {

    @Column
    private String trackerID;

    @Column
    private MetricType metricType;

    @Column
    private Instant timestamp;

    @Column
    private Instant insertTime = LocalDateTime.now().toInstant(ZoneOffset.UTC);

    @Column
    private String metricValue;

    @Column
    private String trackerDataId;

    public TimeSeriesData(){

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

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public Instant getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Instant insertTime) {
        this.insertTime = insertTime;
    }

    public String getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(String metricValue) {
        this.metricValue = metricValue;
    }

    public String getTrackerDataId() {
        return trackerDataId;
    }

    public void setTrackerDataId(String trackerDataId) {
        this.trackerDataId = trackerDataId;
    }
    public static TimeSeriesData toObject(TimeSeriesDataitem timeSeriesDataitem){
        TimeSeriesData timeSeriesData = new TimeSeriesData();
        timeSeriesData.setMetricType(timeSeriesDataitem.getMetricType());
        timeSeriesData.setTimestamp(timeSeriesDataitem.getTimestamp().toInstant(ZoneOffset.UTC));
        timeSeriesData.setInsertTime(LocalDateTime.now().toInstant(ZoneOffset.UTC));
        timeSeriesData.setMetricValue(timeSeriesDataitem.getValue());
        timeSeriesData.setTrackerDataId(timeSeriesDataitem.getTrackerDataId());
        timeSeriesData.setTrackerID(timeSeriesDataitem.getTrackerID());
        return timeSeriesData;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("trackerID", trackerID)
                .append("metricType", metricType)
                .append("metricValue", metricValue)
                .append("timestamp", timestamp)
                .append("insertTime", insertTime)
                .append("trackerDataID", trackerDataId)
                .toString();
    }
}
