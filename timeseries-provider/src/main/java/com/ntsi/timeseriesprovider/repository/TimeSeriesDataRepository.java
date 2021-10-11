package com.ntsi.timeseriesprovider.repository;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.query.FluxTable;
import com.influxdb.query.dsl.Flux;
import com.influxdb.query.dsl.functions.restriction.Restrictions;
import com.ntsi.timeseriesprovider.config.influxdb.InfluxFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Repository
public class TimeSeriesDataRepository {

    private final InfluxDBClient influxDBClient;

    private final InfluxFactory influxFactory;

    public TimeSeriesDataRepository(InfluxDBClient influxDBClient, InfluxFactory influxFactory) {
        this.influxDBClient = influxDBClient;
        this.influxFactory = influxFactory;
    }
    public List<FluxTable> getLatestTrackerData(Long trackerId){
        String query = Flux.from(influxFactory.getBucket())
                .range(LocalDateTime.now().minusDays(100).toInstant(ZoneOffset.UTC), LocalDateTime.now().plusDays(5).toInstant(ZoneOffset.UTC))
                .filter(Restrictions.and(
                        Restrictions.measurement().equal("timeseries"),
                        Restrictions.tag("trackerId").equal(String.valueOf(trackerId))))
                        .toString();
        return influxDBClient.getQueryApi().query(query);
    }
}
