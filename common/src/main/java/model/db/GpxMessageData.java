package model.db;

import model.dto.GPXRequest;
import model.dto.TrackerMessageData;
import model.dto.TrackerMessageDataType;
import model.gson.TrackerGson;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class GpxMessageData extends TrackerMessageData {
    private GPXRequest gpxRequest;

    public GpxMessageData() {
        super(TrackerMessageDataType.GPX);
    }

    public static GpxMessageData fromJson(String jsonData) {
        return TrackerGson.getGson(TrackerGson.GsonAdapter.ISO_8601_NO_MILLI).fromJson(jsonData, GpxMessageData.class);
    }

    public GPXRequest getGpxRequest() {
        return gpxRequest;
    }

    public void setGpxRequest(GPXRequest gpxRequest) {
        this.gpxRequest = gpxRequest;
    }

    private LocalDateTime createdTime = LocalDateTime.now(ZoneOffset.UTC);

    private LocalDateTime updatedTime = LocalDateTime.now(ZoneOffset.UTC);

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("createdTime", createdTime)
                .append("updatedTime", updatedTime)
                .append("GpxRequest", gpxRequest)
                .toString();
    }
}
