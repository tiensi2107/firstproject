package com.ntsi.gpxgateway.service;

import io.jenetics.jpx.GPX;
import model.db.GpxMessageData;
import model.dto.GPXRequest;
import model.dto.TrackerMessageInfo;
import model.rabbitmq.ExchangeName;
import model.rabbitmq.QueueName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class GpxFileService {
    private static Logger log = LogManager.getLogger(GpxFileService.class);

    private final AmqpTemplate amqpTemplate;

    public GpxFileService(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public ResponseEntity<?> handle(MultipartFile multipartFile, String clientId, String clientSecret){
        try (InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes())) {
            GPX gpx = GPX.read(inputStream);

            GPXRequest gpxRequest = new GPXRequest(gpx, clientId, clientSecret, multipartFile.getOriginalFilename());

            GpxMessageData gpxMessageData = new GpxMessageData();
            gpxMessageData.setGpxRequest(gpxRequest);

            TrackerMessageInfo trackerMessageInfo = new TrackerMessageInfo();
            trackerMessageInfo.setTrackerMessageData(gpxMessageData);
            log.info("Receive gpx file request {} from {}", gpxRequest, clientId);
            amqpTemplate.convertAndSend(ExchangeName.RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN,
                    QueueName.RABBITMQ_QUEUE_GPX_REQUEST_MAIN, trackerMessageInfo);
            log.info("Queued gpx file request {}", gpxRequest);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (IOException e) {
            log.error("IO exception occurs when handling the gpx file", e);
            return new ResponseEntity<>("IO exception occurs when handling the gpx file", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Unexpected exception occurs when handling the gpx file", e);
            return new ResponseEntity<>("Unexpected exception occurs when handling the gpx file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
