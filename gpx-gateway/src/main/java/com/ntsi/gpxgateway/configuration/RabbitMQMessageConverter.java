package com.ntsi.gpxgateway.configuration;


import com.google.gson.Gson;
import com.ntsi.gpxgateway.gson.GPXGson;
import com.ntsi.gpxgateway.util.ZonedDateTimeConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.ZonedDateTime;

public class RabbitMQMessageConverter extends Jackson2JsonMessageConverter {
    private static final Logger logger = LogManager.getLogger(RabbitMQMessageConverter.class);

    private Gson gson;



    private void initializeGson(){
        gson = GPXGson.getBuilder().registerTypeAdapter(ZonedDateTime.class,  new ZonedDateTimeConverter()).create();
    }
    public RabbitMQMessageConverter() {
        super();
        DefaultClassMapper mapper = new DefaultClassMapper();
        mapper.setTrustedPackages("*");
        setClassMapper(mapper);
        initializeGson();
    }

    @Override
    public Object fromMessage(Message message){
        try{
            Object content = null;
            MessageProperties messageProperties = message.getMessageProperties();
            if(messageProperties != null){
                String contentType = messageProperties.getContentType();
                if(contentType != null && contentType.contains("json")){
                    String encoding = messageProperties.getContentEncoding();
                    if(encoding == null){
                        encoding = getDefaultCharset();
                    }

                    Class<?> targetClass = getClassMapper().toClass(message.getMessageProperties());
                    content = convertBytestoObject(message.getBody(), encoding, targetClass);
                }else {
                    logger.info("Couldn't convert incoming message with content type [{}]", contentType);
                }
            }
            if(content == null){
                content = message.getBody();
            }
            return content;
        }
        catch (Exception e){
            logger.error("Fail to convert message {}",message , e);
            throw new MessageConversionException("Fail to convert message content", e);
        }
    }
    private Object convertBytestoObject(byte[] body, String encoding, Class<?> targetClass) throws IOException{
        String contentAsString = new String(body, encoding);
        return gson.fromJson(contentAsString,targetClass);
    }

    @Override
    protected Message createMessage(Object objectToConvert, MessageProperties messageProperties, @Nullable Type genericType )  {
       try{
           byte[] bytes;
           String jsonString = gson.toJson(objectToConvert);
           bytes = jsonString.getBytes(getDefaultCharset());
           messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
           messageProperties.setContentEncoding(getDefaultCharset());
           messageProperties.setContentLength(bytes.length);
           getClassMapper().fromClass(objectToConvert.getClass(),messageProperties);
           return new Message(bytes, messageProperties);
       }
       catch (Exception e){
           logger.error("Fail to create message {}",objectToConvert, e);
           throw new MessageConversionException("Fail to convert message content",e);
       }
    }
}
