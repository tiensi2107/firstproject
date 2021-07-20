package com.ntsi.gpxgateway.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.ntsi.gpxgateway.annotation.Suppress;

public class IncludeWithoutSuppressStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes field) {
        Suppress annotation = field.getAnnotation(Suppress.class);
        return annotation != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
