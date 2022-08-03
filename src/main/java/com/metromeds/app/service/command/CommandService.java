
package com.metromeds.app.service.command;

import com.metromeds.app.handlers.CommandNotAvailableException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface CommandService {
    ResponseEntity executeController(String resource, String method, Map<String, String> allParams) throws CommandNotAvailableException, InvocationTargetException, NoSuchMethodException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException;

}