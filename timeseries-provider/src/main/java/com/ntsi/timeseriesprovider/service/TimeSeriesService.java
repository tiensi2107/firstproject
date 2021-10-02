package com.ntsi.timeseriesprovider.service;

import com.influxdb.query.FluxTable;
import com.ntsi.timeseriesprovider.repository.TimeSeriesDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TimeSeriesService {
    private final TimeSeriesDataRepository timeSeriesDataRepository;

    public TimeSeriesService(TimeSeriesDataRepository timeSeriesDataRepository) {
        this.timeSeriesDataRepository = timeSeriesDataRepository;
    }
    public List<FluxTable> getLatestTrackerData(Long trackerId){
        Objects.requireNonNull(trackerId);
        return timeSeriesDataRepository.getLatestTrackerData(trackerId);
    }
}
