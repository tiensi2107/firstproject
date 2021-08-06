package model.gson;

import com.google.gson.*;
import model.dto.TrackerMessageData;
import model.dto.TrackerMessageDataType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Type;

public class GpxMessageDataDeserializer implements JsonDeserializer<TrackerMessageData> , JsonSerializer<TrackerMessageData>{
    private static final Logger log = LogManager.getLogger(GpxMessageDataDeserializer.class);
    @Override
    public TrackerMessageData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        TrackerMessageDataType type = TrackerMessageDataType.valueOf(((JsonObject) json).get(TrackerMessageData.MESSAGE_TYPE).getAsString());
        String jsonData = String.valueOf(json);
        TrackerMessageData trackerMessageData = null;
        try {
            trackerMessageData = TrackerMessageData.getTrackerMessageData(type, jsonData);
        }
        catch (Exception e){
            log.error("Get error when deserialize tracker message data {}"+ e.getMessage());
        }
        return trackerMessageData;
    }

    @Override
    public JsonElement serialize(TrackerMessageData trackerMessageData, Type type, JsonSerializationContext context) {
        JsonElement jsonElement = context.serialize(trackerMessageData, trackerMessageData.getClass());
        jsonElement.getAsJsonObject().get(TrackerMessageData.MESSAGE_TYPE).getAsString();
        return jsonElement;
    }
}
