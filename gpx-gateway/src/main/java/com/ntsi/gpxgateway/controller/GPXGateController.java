package com.ntsi.gpxgateway.controller;

import com.ntsi.gpxgateway.service.GpxFileService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class GPXGateController {
    private final GpxFileService gpxFileService;

    public GPXGateController(GpxFileService gpxFileService) {
        this.gpxFileService = gpxFileService;
    }

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> upload(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "clientId") String clientId, @RequestParam(value = "clientSecret") String clientSecret){
        return gpxFileService.handle(file, clientId, clientSecret);
    }
}
