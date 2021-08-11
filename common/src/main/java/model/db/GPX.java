package model.db;

import model.dto.Metadata;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

public class GPX implements Serializable{


    private String version;


    private Metadata metadata;



    private List<Track> tracks;



    public GPX() {
    }

    public GPX(io.jenetics.jpx.GPX gpx) {
        this.version = gpx.getVersion();
        this.metadata = new Metadata(gpx.getMetadata().orElse(null));


        List<io.jenetics.jpx.Track> tracks = gpx.getTracks();
        if (CollectionUtils.isNotEmpty(tracks)) {
            List<Track> newTracks = new ArrayList<>();
            tracks.forEach(track -> {
                newTracks.add(new Track(track));
            });
            this.tracks = newTracks;
        }
    }

    private LocalDateTime createdTime = LocalDateTime.now(ZoneOffset.UTC);

    private LocalDateTime updatedTime = LocalDateTime.now(ZoneOffset.UTC);


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
                .append("version", version)
                .append("metadata", metadata)
                .append("tracks", tracks)
                .append("createdTime", createdTime)
                .append("updatedTime", updatedTime)
                .toString();
    }
}
