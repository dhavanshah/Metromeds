package com.metromeds.app.handlers;

import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface CommandHandler {
    ResponseEntity execute(String method, Map<String, String> parms) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException, IOException;
}