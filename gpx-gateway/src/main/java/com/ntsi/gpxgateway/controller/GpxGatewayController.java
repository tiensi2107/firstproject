package com.ntsi.gpxgateway.controller;


import com.ntsi.gpxgateway.service.GpxGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
public class GpxGatewayController {
    GpxGatewayService gpxGatewayService;
    public GpxGatewayController(GpxGatewayService gpxGatewayService){
        this.gpxGatewayService = gpxGatewayService;
    }

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "clientID") String clientID, @RequestParam("clientSecret") String clientSecret) throws IOException {

       return gpxGatewayService.handleFile(file, clientID, clientSecret);

    }



}
