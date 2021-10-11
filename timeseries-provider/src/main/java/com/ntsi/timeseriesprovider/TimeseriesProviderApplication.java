package com.ntsi.timeseriesprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class TimeseriesProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeseriesProviderApplication.class, args);
    }

}
