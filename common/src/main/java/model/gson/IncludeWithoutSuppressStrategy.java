package model.gson;

import annotation.Suppress;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class IncludeWithoutSuppressStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes field) {
        Suppress annotation = field.getAnnotation(Suppress.class);
        return annotation != null;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
