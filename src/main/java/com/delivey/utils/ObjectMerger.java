package com.delivey.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Map;

public class ObjectMerger<T> {

    private ObjectMapper objectMapper;
    private Class<T> type;

    private ObjectMerger(Class<T> type) {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
        this.type = type;
    }

    public static ObjectMerger of(Class type) {
        return new ObjectMerger(type);
    }

    public void mergeRequestBodyToGenericObject(Map<String, Object> objectMap, T objectToUpdate, HttpServletRequest request) {
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);
        try {
            T newObject = objectMapper.convertValue(objectMap, type);

            objectMap.forEach((fieldProp, valueProp) -> {
                Field field = ReflectionUtils.findField(type, fieldProp);
                field.setAccessible(true);

                Object newValue = ReflectionUtils.getField(field, newObject);

                ReflectionUtils.setField(field, objectToUpdate, newValue);
            });
        } catch (IllegalArgumentException ex) {
            Throwable rootCause = ExceptionUtils.getRootCause(ex);
            throw new HttpMessageNotReadableException(ex.getMessage(), rootCause, serverHttpRequest);
        }
    }

}