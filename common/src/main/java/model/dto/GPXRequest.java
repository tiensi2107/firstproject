package model.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GPXRequest {
    private String clientId;
    private String clientSecret;
    private String filename;
    private long consumerAttempts;
    private model.db.GPX gpxModel;

    public GPXRequest() {
    }

    public GPXRequest(model.db.GPX gpxModel, String clientId, String filename) {
        this.gpxModel = gpxModel;
        this.clientId = clientId;
        this.filename = filename;
    }

    public GPXRequest(model.db.GPX gpxModel, String clientId, String clientSecret, String filename) {
        this.gpxModel = gpxModel;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.filename = filename;
    }

    public model.db.GPX getGpxModel() {
        return gpxModel;
    }

    public void setGpxModel(model.db.GPX gpxModel) {
        this.gpxModel = gpxModel;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public long getConsumerAttempts() {
        return consumerAttempts;
    }

    public void setConsumerAttempts(long consumerAttempts) {
        this.consumerAttempts = consumerAttempts;
    }

    public void increaseAttempts() {
        this.consumerAttempts++;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("clientId", clientId)
                .append("clientSecret", clientSecret)
                .append("filename", filename)
                .append("consumerAttempts", consumerAttempts)
                .append("gpx", gpxModel)
                .toString();
    }
}
