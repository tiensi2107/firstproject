package model.dto;

import model.db.TimeSeriesDataitem;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeoTimeSeriesObject {
    List<TimeSeriesDataitem> timeSeriesDataitems = new ArrayList<>();

    public GeoTimeSeriesObject(List<TimeSeriesDataitem> timeSeriesDataitems) {
        this.timeSeriesDataitems = timeSeriesDataitems;
    }
    public GeoTimeSeriesObject(TimeSeriesDataitem timeSeriesDataitem){
        this.timeSeriesDataitems = Collections.singletonList(timeSeriesDataitem);
    }
    public GeoTimeSeriesObject() {
    }

    public List<TimeSeriesDataitem> getTimeSeriesDataitems() {
        return timeSeriesDataitems;
    }

    public void setTimeSeriesDataitems(List<TimeSeriesDataitem> timeSeriesDataitems) {
        this.timeSeriesDataitems = timeSeriesDataitems;
    }
    public void add(TimeSeriesDataitem timeSeriesDataitem){
        this.timeSeriesDataitems.add(timeSeriesDataitem);
    }
    public void add(List<TimeSeriesDataitem> timeSeriesDataitems){
        this.timeSeriesDataitems.addAll(timeSeriesDataitems);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("timeseriesdataitems", timeSeriesDataitems)
                .toString();
    }
}
