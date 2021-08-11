package model.db;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TrackSegment implements Serializable {

    private Long id;


    private List<WayPoint> points;

    private LocalDateTime insertedTime = LocalDateTime.now(ZoneOffset.UTC);


    public TrackSegment() {
    }

   public TrackSegment(io.jenetics.jpx.TrackSegment trackSegment) {
        List<io.jenetics.jpx.WayPoint> wayPoints = trackSegment.getPoints();
        if (CollectionUtils.isNotEmpty(wayPoints)) {
            List<WayPoint> newWayPoints = new ArrayList<>();
            wayPoints.forEach(wayPoint -> {
                newWayPoints.add(new WayPoint(wayPoint));
            });
            this.points = newWayPoints;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<WayPoint> getPoints() {
        return points;
    }

    public void setPoints(List<WayPoint> points) {
        this.points = points;
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
        TrackSegment that = (TrackSegment) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("points", points)
                .append("insertedTime", insertedTime)
                .toString();
    }
}
