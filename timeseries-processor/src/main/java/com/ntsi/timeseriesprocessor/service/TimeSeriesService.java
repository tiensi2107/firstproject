package com.ntsi.timeseriesprocessor.service;

import com.ntsi.timeseriesprocessor.model.TimeSeriesData;
import com.ntsi.timeseriesprocessor.repository.TimeSeriesDataRepository;
import model.db.TimeSeriesDataitem;
import model.dto.ArchivingTimeSeriesObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeSeriesService {
    private final TimeSeriesDataRepository timeSeriesDataRepository;

    public TimeSeriesService(TimeSeriesDataRepository timeSeriesDataRepository) {
        this.timeSeriesDataRepository = timeSeriesDataRepository;
    }

    public void save(ArchivingTimeSeriesObject archivingTimeSeriesObject){
        List<TimeSeriesDataitem> timeSeriesDataitems = archivingTimeSeriesObject.getTimeSeriesDataitems();
        if(CollectionUtils.isNotEmpty(timeSeriesDataitems)){
            List<TimeSeriesData> newTimeSeriesDataList = new ArrayList<>();
            for(TimeSeriesDataitem timeSeriesDataitem : timeSeriesDataitems){
                newTimeSeriesDataList.add(TimeSeriesData.toObject(timeSeriesDataitem));
            }
            timeSeriesDataRepository.save(newTimeSeriesDataList);
        }
    }
}
