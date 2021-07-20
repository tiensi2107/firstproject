package com.ntsi.gpxgateway.dto;

import io.jenetics.jpx.GPX;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class GpxGatewayDTO {
    private GPX gpx;
    private String clientID;
    private String clientSecret;
    private String filename;
    private long consumerAttemps;

    public GPX getGpx() {
        return gpx;
    }

    public void setGpx(GPX gpx) {
        this.gpx = gpx;
    }

    public GpxGatewayDTO(GPX gpx, String clientID, String clientSecret) {

        this.gpx = gpx;
        this.clientID = clientID;
        this.clientSecret = clientSecret;
    }
    public GpxGatewayDTO(GPX gpx, String clientID, String clientSecretn, String filename){
        this.gpx = gpx;
        this.clientID = clientID;
        this.clientSecret = clientSecretn;
        this.filename = filename;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public long getConsumerAttemps() {
        return consumerAttemps;
    }

    public void setConsumerAttemps(long consumerAttemps) {
        this.consumerAttemps = consumerAttemps;
    }
    public void increaseAttemps(){
        this.consumerAttemps++;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("clientID",clientID)
                .append("filename",filename)
                .append("consumerAttemps",consumerAttemps)
                .toString();
    }
}
