package model.dto;

import model.db.TimeSeriesDataitem;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArchivingTimeSeriesObject {
    private List<TimeSeriesDataitem> timeSeriesDataitems = new ArrayList<>();

    public ArchivingTimeSeriesObject() {
    }

    public ArchivingTimeSeriesObject(TimeSeriesDataitem timeSeriesDataitems) {
        this.timeSeriesDataitems = Collections.singletonList(timeSeriesDataitems);
    }
    public ArchivingTimeSeriesObject(List<TimeSeriesDataitem> timeSeriesDataItems) {
        this.timeSeriesDataitems = timeSeriesDataItems;
    }

    public List<TimeSeriesDataitem> getTimeSeriesDataitems() {
        return timeSeriesDataitems;
    }

    public void setTimeSeriesDataitems(List<TimeSeriesDataitem> timeSeriesDataItems) {
        this.timeSeriesDataitems = timeSeriesDataItems;
    }

    public void add(TimeSeriesDataitem timeSeriesDataitem) {
        this.timeSeriesDataitems.add(timeSeriesDataitem);
    }

    public void add(List<TimeSeriesDataitem> timeSeriesDataitems) {
        this.timeSeriesDataitems.addAll(timeSeriesDataitems);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("timeSeriesDataitems", timeSeriesDataitems)
                .toString();
    }
}
