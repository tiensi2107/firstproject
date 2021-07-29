package com.ntsi.messageprocessor.model.db;


import com.ntsi.messageprocessor.model.dto.Link;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import java.util.List;



public class Track implements Serializable {

    private Long id;


    private String name;


    private String comment;


    private String description;


    private String source;


    private List<Link> links;


    private String type;


    private List<TrackSegment> segments;


    private LocalDateTime insertedTime = LocalDateTime.now(ZoneOffset.UTC);

    public Track() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<TrackSegment> getSegments() {
        return segments;
    }

    public void setSegments(List<TrackSegment> segments) {
        this.segments = segments;
    }


    public LocalDateTime getInsertedTime() {
        return insertedTime;
    }

    public void setInsertedTime(LocalDateTime createdTime) {
        this.insertedTime = createdTime;
    }



    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("comment", comment)
                .append("description", description)
                .append("source", source)
                .append("links", links)
                .append("type", type)
                .append("segments", segments)
                .append("insertedTime", insertedTime)
                .toString();
    }
}
