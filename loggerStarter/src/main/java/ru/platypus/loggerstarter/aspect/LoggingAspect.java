package ru.platypus.loggerstarter.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.platypus.loggerstarter.config.LoggingProperties;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;

@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private final LoggingProperties loggingProperties;

    public LoggingAspect(LoggingProperties loggingProperties) {
        this.loggingProperties = loggingProperties;
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restControllerMethods() {
    }

    @Around("restControllerMethods()")
    public Object logHttpRequests(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!loggingProperties.isEnabled()) {
            return joinPoint.proceed();
        }

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return joinPoint.proceed();
        }
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();

        if (request != null) {
            logger.info("HTTP Request: {} {}", request.getMethod(), request.getRequestURI());

            if (loggingProperties.getDetailLevel() == LoggingProperties.DetailLevel.FULL) {
                // Логирование заголовков запроса
                Collections.list(request.getHeaderNames())
                        .forEach(headerName -> logger.info("Request Header: {}={}", headerName, request.getHeader(headerName)));

                // Логирование параметров запроса
                request.getParameterMap().forEach((key, values) -> {
                    for (String value : values) {
                        logger.info("Request Parameter: {}={}", key, value);
                    }
                });

                // Логирование тела запроса
                String requestBody = getRequestBody(request);
                if (requestBody != null) {
                    logger.info("Request Body: {}", requestBody);
                } else {
                    logger.warn("Request Body is not available for logging");
                }
            }
        }

        Object result = joinPoint.proceed();

        if (response != null && loggingProperties.getDetailLevel() == LoggingProperties.DetailLevel.FULL) {
            // Логирование статуса ответа
            logger.info("HTTP Response Status: {}", response.getStatus());

            // Логирование заголовков ответа
            response.getHeaderNames().forEach(headerName -> logger.info("Response Header: {}={}", headerName, response.getHeader(headerName)));
        }

        return result;
    }

    private String getRequestBody(HttpServletRequest request) {
        try {
            BufferedReader reader = request.getReader();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            logger.error("Error reading request body: {}", e.getMessage());
            return null;
        }
    }


}
