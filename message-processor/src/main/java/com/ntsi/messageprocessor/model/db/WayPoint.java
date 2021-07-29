package com.ntsi.messageprocessor.model.db;


import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDateTime;


public class WayPoint  {
    private Long id;

    private Double latitude;

    private Double longitude;

    private Double elevation;

    private Double speed;

    private LocalDateTime time;


    private String name;


    private String comment;


    private String description;


    private String source;




    private String symbol;


    private String type;




    public WayPoint() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }



    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("latitude", latitude)
                .append("longitude", longitude)
                .append("elevation", elevation)
                .append("speed", speed)
                .append("time", time)
                .append("name", name)
                .append("comment", comment)
                .append("description", description)
                .append("source", source)
                .append("symbol", symbol)
                .append("type", type)
                .toString();
    }

}
