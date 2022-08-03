package com.metromeds.app.interceptor.request;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.metromeds.app.handlers.CommandNotAvailableException;
import com.metromeds.app.service.command.CommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiRequest {
    @Autowired
    CommandService commandService;
    public enum ResponseStatus {SUCCESS, ERROR, WARNING, NO_ACCESS}
    final String constOperation = "operation";
    @GetMapping(value = "/api/{resource}")
    public ResponseEntity processGetRequest( @PathVariable String resource, @RequestParam Map<String, String> allParams) throws CommandNotAvailableException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
        return commandService.executeController(resource, "get", allParams);
    }
    @PostMapping(value = "/api/{resource}")
    public ResponseEntity processPostRequest(@RequestBody Map<String, String> allParams, @PathVariable String resource, @RequestParam Map<String, String> query) throws CommandNotAvailableException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
        if (query.size() > 0 && query.containsKey(constOperation)) {
            allParams.put(constOperation, query.get(constOperation));
        }
        return commandService.executeController(resource, "post", allParams);
    }
    @DeleteMapping(value = "/api/{resource}")
    public ResponseEntity processDeleteRequest(@RequestParam Map<String, String> allParams, @PathVariable String resource) throws CommandNotAvailableException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException {
        return commandService.executeController(resource, "delete", allParams);
    }
}