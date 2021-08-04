package model.db;

import model.dto.Metadata;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Tracker {
    private String id;

    private String driverID;

    private String groupID;

    private String fileName;


    private String version;


    private Metadata metadata;




    private List<Track> tracks;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
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


    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("diverId", driverID)
                .append("groupId", groupID)
                .append("filename", fileName)
                .append("track", tracks)
                .append("version", version)
                .append("metadata", metadata)
                .toString();
    }
}
