package model.dto;

import model.db.TimeSeriesDataitem;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

public class ArchivingTimeSeriesObject {
    private List<TimeSeriesDataitem> timeSeriesDataitems = new ArrayList<>();

    public ArchivingTimeSeriesObject(List<TimeSeriesDataitem> timeSeriesDataitems) {
        this.timeSeriesDataitems = timeSeriesDataitems;
    }

    public ArchivingTimeSeriesObject() {
    }

    public List<TimeSeriesDataitem> getTimeSeriesDataitems() {
        return timeSeriesDataitems;
    }

    public void setTimeSeriesDataitems(List<TimeSeriesDataitem> timeSeriesDataitems) {
        this.timeSeriesDataitems = timeSeriesDataitems;
    }
    public ArchivingTimeSeriesObject(TimeSeriesDataitem timeSeriesDataitem){
        this.timeSeriesDataitems = Collections.singletonList(timeSeriesDataitem);
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
                .append("timeseriesdataitem", timeSeriesDataitems)
                .toString();
    }
}
