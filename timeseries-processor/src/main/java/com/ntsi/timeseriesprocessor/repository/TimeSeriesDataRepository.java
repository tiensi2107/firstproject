package com.ntsi.timeseriesprocessor.repository;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApi;
import com.influxdb.client.domain.WritePrecision;
import com.ntsi.timeseriesprocessor.model.TimeSeriesData;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TimeSeriesDataRepository {

    private final InfluxDBClient influxDBClient;

    public TimeSeriesDataRepository(InfluxDBClient influxDBClient) {
        this.influxDBClient = influxDBClient;
    }
    public void save(List<TimeSeriesData> timeSeriesDataList){
        try (WriteApi writeApi = influxDBClient.getWriteApi()){
            writeApi.writeMeasurements(WritePrecision.NS, timeSeriesDataList);
        }
    }
}
