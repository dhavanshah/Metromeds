package com.metromeds.app.service;

import com.metromeds.app.service.converters.stringConverter;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.Map;

public class helper {
    public static Object getVariables(Object obj,
                                      Map<String, String> params) throws ParseException, NoSuchFieldException, IllegalAccessException {
        for (Map.Entry<String,String> entry : params.entrySet()) {
            Field variable = obj.getClass().getDeclaredField(entry.getKey());
            variable.setAccessible(true);
            Class<?> dataType = variable.getType();

            variable.set(obj, new stringConverter().convert(dataType, entry.getValue()));
        }
        return obj;
    }
}
