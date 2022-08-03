package com.metromeds.app.interceptor.response;

import com.metromeds.app.interceptor.request.ApiRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApiResponse {
    public ResponseEntity setServletResponse(Object responseBody) {
        return ResponseEntity.ok()
                .header(String.valueOf(ApiRequest.ResponseStatus.SUCCESS))
                .body(responseBody);
    }
}
