package com.ntsi.gpxgateway.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

public class GenericEnumAdapterFactory implements TypeAdapterFactory {
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
       Class<? super T> rawType = typeToken.getRawType();
       if(rawType.isEnum()){
           return new GenericEnumAdaper<>(typeToken);
       }
       return null;
    }
}
