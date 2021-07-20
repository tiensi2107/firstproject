package com.ntsi.gpxgateway.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;


public class GenericEnumAdaper <T> extends TypeAdapter<T> {
    private Map<String, T> values = new HashMap<>();

    private TypeToken<T> typeToken;
    public GenericEnumAdaper(TypeToken<T> typeToken){
        Class<T> rawType = (Class<T>) typeToken.getRawType();
        for(T t : rawType.getEnumConstants()){
            values.put(t.toString(),t);
        }
    }

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        if(value == null){
            out.nullValue();
            return;
        }
        out.beginObject();
        out.name("name");
        out.value(value.toString());
        Field[] fields = value.getClass().getDeclaredFields();
        for(Field field : fields){
            if(!Modifier.isStatic(field.getModifiers())){
                if(field.trySetAccessible()){
                    Object fieldValue;
                    try{
                        fieldValue = field.get(value);
                        if(fieldValue != null){
                            out.name(field.getName());
                            out.name(fieldValue.toString());
                        }
                        }
                    catch(IllegalAccessException e){
                        throw new IOException();
                    }
                }
            }
        }
        out.endObject();

    }

    @Override
    public T read(JsonReader in) throws IOException {
        T value = null;
        if(in.peek() == JsonToken.NULL){
            in.nextNull();
        }else if(in.peek() == JsonToken.BEGIN_OBJECT){
            in.beginObject();
            while (in.hasNext()){
                String filed = in.nextName();
                if("name".equals(filed)){
                    value = values.get(in.nextString());
                }
            }
            in.endObject();
        }
        return value;
    }
}
