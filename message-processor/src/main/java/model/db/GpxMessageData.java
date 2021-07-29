package model.db;


import model.dto.Metadata;

import model.dto.TrackerDataType;
import model.dto.TrackerMessageData;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import java.util.List;



public class GpxMessageData extends TrackerMessageData {

    private Long id;


    private String fileName;


    private String version;


    private Metadata metadata;


    private List<WayPoint> wayPoints;


    private List<Track> tracks;




    private LocalDateTime createdTime = LocalDateTime.now(ZoneOffset.UTC);

    private LocalDateTime updatedTime = LocalDateTime.now(ZoneOffset.UTC);

    public GpxMessageData() {
        super(TrackerDataType.GPX);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<WayPoint> getWayPoints() {
        return wayPoints;
    }

    public void setWayPoints(List<WayPoint> wayPoints) {
        this.wayPoints = wayPoints;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

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
                .append("id", id)
                .append("fileName", fileName)
                .append("version", version)
                .append("metadata", metadata)
                .append("wayPoints", wayPoints)
                .append("tracks", tracks)
                .append("createdTime", createdTime)
                .append("updatedTime", updatedTime)
                .toString();
    }

}
