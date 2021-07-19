package com.ntsi.gpxgateway.dto;

import io.jenetics.jpx.GPX;

public class GpxGatewayDTO {
    private GPX gpx;

    public GPX getGpx() {
        return gpx;
    }

    public void setGpx(GPX gpx) {
        this.gpx = gpx;
    }

    public GpxGatewayDTO(GPX gpx) {
        this.gpx = gpx;
    }
}
