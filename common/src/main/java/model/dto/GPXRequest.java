package model.dto;

import io.jenetics.jpx.GPX;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class GPXRequest {
    private GPX gpx;
    private String clientId;
    private String clientSecret;
    private String filename;
    private long consumerAttempts;

    public GPXRequest() {
    }

    public GPXRequest(GPX gpx, String clientId, String filename) {
        this.gpx = gpx;
        this.clientId = clientId;
        this.filename = filename;
    }

    public GPXRequest(GPX gpx, String clientId, String clientSecret, String filename) {
        this.gpx = gpx;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.filename = filename;
    }
    public GPXRequest(GPXRequest gpxRequest){
        this.gpx = gpxRequest.gpx;
        this.clientId = gpxRequest.clientId;
        this.clientSecret = gpxRequest.clientSecret;
        this.filename = gpxRequest.filename;
    }

    public GPX getGpx() {
        return gpx;
    }

    public void setGpx(GPX gpx) {
        this.gpx = gpx;
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
                .append("gpx", gpx)
                .toString();
    }
}
