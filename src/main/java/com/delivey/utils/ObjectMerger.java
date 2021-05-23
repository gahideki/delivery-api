package com.delivey.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

public class ObjectMerger<T> {

    private ObjectMapper objectMapper;
    private Class<T> type;

    private ObjectMerger(Class<T> type) {
        this.objectMapper = new ObjectMapper();
        this.type = type;
    }

    public static ObjectMerger of(Class type) {
        return new ObjectMerger(type);
    }

    public void mergeRequestBodyToGenericObject(Map<String, Object> objectMap, T objectToUpdate) {
        T newObject = objectMapper.convertValue(objectMap, type);

        objectMap.forEach((fieldProp, valueProp) -> {
            Field field = ReflectionUtils.findField(type, fieldProp);
            field.setAccessible(true);

            Object newValue = ReflectionUtils.getField(field, newObject);

            ReflectionUtils.setField(field, objectToUpdate, newValue);
        });
    }

}