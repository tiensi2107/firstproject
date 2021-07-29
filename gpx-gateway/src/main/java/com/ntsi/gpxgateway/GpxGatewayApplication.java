package com.ntsi.gpxgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.ntsi")
public class GpxGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpxGatewayApplication.class, args);
    }

}
