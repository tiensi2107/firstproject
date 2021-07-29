package com.ntsi.messageprocessor.model.db;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import java.util.List;



public class TrackSegment implements Serializable {

    private Long id;


    private List<WayPoint> points;

    private LocalDateTime insertedTime = LocalDateTime.now(ZoneOffset.UTC);


    private Track track;

    public TrackSegment() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<WayPoint> getPoints() {
        return points;
    }

    public void setPoints(List<WayPoint> points) {
        this.points = points;
    }

    public LocalDateTime getInsertedTime() {
        return insertedTime;
    }

    public void setInsertedTime(LocalDateTime createdTime) {
        this.insertedTime = createdTime;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("points", points)
                .append("insertedTime", insertedTime)
                .append("track", track)
                .toString();
    }
}
