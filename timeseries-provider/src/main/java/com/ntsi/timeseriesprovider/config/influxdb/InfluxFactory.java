package com.ntsi.timeseriesprovider.config.influxdb;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxFactory {

    @Value("${influxdb.host}")
    private String host;

    @Value("${influxdb.org}")
    private String org;

    @Value("${influxdb.bucket}")
    private String bucket;

    @Value("${influxdb.token}")
    private String token;

    @Bean
    public InfluxDBClient influxDBClient(){
        return InfluxDBClientFactory.create(host, token.toCharArray(), bucket, org);
    }
    public String getHost() {
        return host;
    }

    public String getOrg() {
        return org;
    }

    public String getBucket() {
        return bucket;
    }


    public String getToken() {
        return token;
    }
}
