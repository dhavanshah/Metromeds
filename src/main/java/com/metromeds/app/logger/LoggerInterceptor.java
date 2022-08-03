package com.metromeds.app.logger;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

public class LoggerInterceptor {
    long currentTime;
    final String constStartTime = "startTime";
    final String constReqUrl = "Request URL:";
    private static final Logger LOGGER = Logger.getLogger(LoggerInterceptor.class.getName());
    private void setTime(long currentTime) {
        this.currentTime = currentTime;
    }

    private void logPreRequestTime(HttpServletRequest request) {
        request.setAttribute(constStartTime, currentTime);
        LOGGER.info(constReqUrl + request.getServletPath() + ": Start Time=" + request.getAttribute(constStartTime));
    }

    private void logPostRequestTime(HttpServletRequest request) {
        LOGGER.info(constReqUrl + request.getServletPath() + " Sent to Handler: Current Time=" + currentTime);
    }

    private void logResponseTime(HttpServletRequest request) {
        long startTime = (long) request.getAttribute(constStartTime);
        LOGGER.info(constReqUrl + request.getServletPath() + ": Time Taken=" + (currentTime - startTime));
    }

    public void processLog(String process, HttpServletRequest request) {
        this.setTime(System.currentTimeMillis());
        if (process.equals("pre")) {
            this.logPreRequestTime(request);
        } else if (process.equals("post")) {
            this.logPostRequestTime(request);
        } else {
            this.logResponseTime(request);
        }
    }
}
