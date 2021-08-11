package model.db;

import io.jenetics.jpx.Latitude;
import io.jenetics.jpx.Longitude;
import model.dto.Link;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WayPoint implements Serializable {

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


    private List<Link> links;


    private String symbol;


    private String type;



    public WayPoint() {
    }



   public WayPoint(io.jenetics.jpx.WayPoint wayPoint) {
        this.update(wayPoint);
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

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WayPoint wayPoint = (WayPoint) o;
        return id.equals(wayPoint.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
                .append("links", links)
                .append("symbol", symbol)
                .append("type", type)
                .toString();
    }



    public void update(io.jenetics.jpx.WayPoint wayPoint) {
        Latitude latitude = wayPoint.getLatitude();
        Longitude longitude = wayPoint.getLongitude();
        if (latitude != null && longitude != null) {
            this.latitude = latitude.doubleValue();
            this.longitude = longitude.doubleValue();
            wayPoint.getComment().ifPresent(this::setComment);
            wayPoint.getDescription().ifPresent(this::setDescription);
            wayPoint.getName().ifPresent(this::setName);
            wayPoint.getSource().ifPresent(this::setSource);
            wayPoint.getType().ifPresent(this::setType);
            wayPoint.getTime().ifPresent(zonedDateTime -> this.time = LocalDateTime.ofInstant(zonedDateTime.toInstant(), ZoneOffset.UTC));
            List<io.jenetics.jpx.Link> links = wayPoint.getLinks();
            if (CollectionUtils.isNotEmpty(links)) {
                this.links = links.stream().map(Link::new).collect(Collectors.toList());
            }

            wayPoint.getSpeed().ifPresent(speed -> this.speed = speed.doubleValue());
            wayPoint.getElevation().ifPresent(length -> this.elevation = length.doubleValue());
            wayPoint.getSymbol().ifPresent(this::setSymbol);
        }
    }
}
