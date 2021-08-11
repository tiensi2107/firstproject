package model.db;

import model.dto.Link;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Track(io.jenetics.jpx.Track track) {
        track.getComment().ifPresent(this::setComment);
        track.getDescription().ifPresent(this::setDescription);
        track.getName().ifPresent(this::setName);
        track.getSource().ifPresent(this::setSource);
        track.getType().ifPresent(this::setType);
        List<io.jenetics.jpx.Link> links = track.getLinks();
        if (CollectionUtils.isNotEmpty(links)) {
            List<Link> newLinks = new ArrayList<>();
            links.forEach(link -> {
                newLinks.add(new Link(link));
            });
            this.links = newLinks;
        }


        List<io.jenetics.jpx.TrackSegment> segments = track.getSegments();
        if (CollectionUtils.isNotEmpty(segments)) {
            List<TrackSegment> trackSegments = new ArrayList<>();
            segments.forEach(segment -> {
                trackSegments.add(new TrackSegment(segment));
            });
            this.segments = trackSegments;
        }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return id.equals(track.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
