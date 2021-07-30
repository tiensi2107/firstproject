package com.ntsi.gpxgateway.service;

import com.ntsi.gpxgateway.dto.*;
import com.ntsi.gpxgateway.rabbitmq.ExchangeName;
import com.ntsi.gpxgateway.rabbitmq.QueueName;
import io.jenetics.jpx.GPX;
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
public class GpxGatewayService {
    private static final Logger logger = LogManager.getLogger(GpxGatewayService.class);

    private final AmqpTemplate amqpTemplate;

    public GpxGatewayService(AmqpTemplate amqpTemplate){

        this.amqpTemplate = amqpTemplate;
    }
    public ResponseEntity<?> handleFile(MultipartFile multipartFile, String clientID, String clientSecret) throws IOException {
        try (InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes())) {
            GPX gpx = GPX.read(inputStream);
            GpxGatewayDTO gpxGatewayDTO = new GpxGatewayDTO(gpx, clientID, clientSecret, multipartFile.getOriginalFilename());

            GpxMessageData gpxMessageData = new GpxMessageData();
            gpxMessageData.setGpxGatewayDTO(gpxGatewayDTO);

            TrackerMessageInfo trackerMessageInfo = new TrackerMessageInfo();
            trackerMessageInfo.setTrackerMessageData(gpxMessageData);


            logger.info("Receive gpx file request {} from {}",gpxGatewayDTO, clientID);
            amqpTemplate.convertAndSend(ExchangeName.RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN, QueueName.RABBITMQ_QUEUE_GPX_REQUEST_MAIN, trackerMessageInfo);
            logger.info("Queue receive request {}", gpxGatewayDTO);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (IOException e) {
            logger.error("IO exception occur when handling the gpx file", e);
            return new ResponseEntity<>("IO exception occur when handling the gpx file",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception e){
            logger.error("Unexpect exception occur when handling the gpx file", e);
            return new ResponseEntity<>("Unexpect exception occur when handling the gpx file",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
