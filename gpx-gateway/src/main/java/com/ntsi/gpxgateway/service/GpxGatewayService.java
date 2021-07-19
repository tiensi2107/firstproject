package com.ntsi.gpxgateway.service;

import com.ntsi.gpxgateway.dto.GpxGatewayDTO;
import com.ntsi.gpxgateway.rabbitmq.ExchangeName;
import com.ntsi.gpxgateway.rabbitmq.QueueName;
import io.jenetics.jpx.GPX;
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
    private final AmqpTemplate amqpTemplate;

    public GpxGatewayService(AmqpTemplate amqpTemplate){
        this.amqpTemplate = amqpTemplate;
    }

    public ResponseEntity<?> uploadFile(MultipartFile multipartFile) throws IOException {
        try (InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes())) {
            GPX gpx = GPX.read(inputStream);
            GpxGatewayDTO gpxGatewayDTO = new GpxGatewayDTO(gpx);
            amqpTemplate.convertAndSend(ExchangeName.RABBITMQ_EXCHANGE_DIRECT_REQUEST_MAIN, QueueName.RABBITMQ_QUEUE_GPX_REQUEST_MAIN, gpxGatewayDTO);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (IOException e) {
            return new ResponseEntity<>("erorr",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
