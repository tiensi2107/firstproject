package com.ntsi.messageprocessor.service.messagehandling;

import com.ntsi.messageprocessor.model.db.*;
import com.ntsi.messageprocessor.model.dto.TrackerMassage;
import com.ntsi.messageprocessor.model.rabbitmq.QueueName;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;


@Service("gpxMessageHandler")
public class GpxMessageHandler extends TrackerMessageHandler{


    private AmqpTemplate amqpTemplate;

    public GpxMessageHandler(AmqpTemplate amqpTemplate){
        this.amqpTemplate = amqpTemplate;
    }

    @Override
    public void handle(TrackerMassage trackerMassage) {
        GpxMessageData gpxMessageDataData = (GpxMessageData) trackerMassage.getTrackerMessageData();

        List<Track> tracks = gpxMessageDataData.getTracks();
        if (CollectionUtils.isNotEmpty(tracks)) {
            tracks.forEach(track -> {
                List<TrackSegment> segments = track.getSegments();
                if (CollectionUtils.isNotEmpty(segments)){
                    segments.forEach(trackSegment -> {
                        List<WayPoint> points = trackSegment.getPoints();
                        if (CollectionUtils.isNotEmpty(points)){
                            points.forEach(wayPoint -> {
                                EnumMap<MetricType, String> values = new EnumMap<>(MetricType.class);

                                values.put(MetricType.TRACKER_DATA_GPS_LOCATION_LAT_LNG, String.valueOf(wayPoint.getLatitude()+ "," + wayPoint.getLongitude()));
                                values.put(MetricType.TRACKER_DATA_GPS_ELEVATION, String.valueOf(wayPoint.getElevation()));
                                values.put(MetricType.TRACKER_DATA_GPS_SPEED, String.valueOf(wayPoint.getSpeed()));
                                values.put(MetricType.TRACKER_DATA_GPX_TIME, String.valueOf(wayPoint.getTime()));
                                values.put(MetricType.TRACKER_DATA_GPS_NAME, wayPoint.getName());
                                values.put(MetricType.TRACKER_DATA_GPS_COMMENT, wayPoint.getComment());
                                values.put(MetricType.TRACKER_DATA_GPS_DESCRIPTION, wayPoint.getDescription());
                                values.put(MetricType.TRACKER_DATA_GPS_SOURCE, wayPoint.getSource());
                                values.put(MetricType.TRACKER_DATA_GPS_SYMBOL, wayPoint.getSymbol());
                                values.put(MetricType.TRACKER_DATA_GPS_TYPE, wayPoint.getType());


                                    amqpTemplate.convertAndSend(QueueName.TRACKER_MESSAGE_MAIN, values);


                                insertMultiple(values, gpxMessageDataData.getTimestamp(), trackerMassage);
                            });
                        }
                    });
                }
            });
        }
    }
}