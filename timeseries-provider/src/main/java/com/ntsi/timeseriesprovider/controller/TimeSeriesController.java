package com.ntsi.timeseriesprovider.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ntsi.timeseriesprovider.service.TimeSeriesService;
import model.gson.TrackerGson;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/timeseries")
public class TimeSeriesController {
    private final TimeSeriesService timeSeriesService;

    public TimeSeriesController(TimeSeriesService timeSeriesService) {
        this.timeSeriesService = timeSeriesService;
    }
    private Gson getGson(){
        GsonBuilder builder = TrackerGson.getBuilder(TrackerGson.GsonAdapter.ISO_8601_NO_MILLI);
        return builder.create();
    }
    @GetMapping(path = "{trackerId}/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLatestTrackerData(@PathVariable Long trackreId){
        return new ResponseEntity<>(getGson().toJson(timeSeriesService.getLatestTrackerData(trackreId)), HttpStatus.OK);
    }
}
