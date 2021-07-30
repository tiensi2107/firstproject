package com.ntsi.gpxgateway.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GpxMessageData extends TrackerMessageData{
    private GpxGatewayDTO gpxGatewayDTO;


    public GpxMessageData() {
        super(TrackerMessageDataType.GPX);
    }


    public GpxGatewayDTO getGpxGatewayDTO() {
        return gpxGatewayDTO;
    }

    public void setGpxGatewayDTO(GpxGatewayDTO gpxGatewayDTO) {
        this.gpxGatewayDTO = gpxGatewayDTO;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("GpxgatewayDTO", gpxGatewayDTO)
                .toString();
    }
}
