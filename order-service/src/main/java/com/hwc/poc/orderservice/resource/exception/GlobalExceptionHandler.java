package com.hwc.poc.orderservice.resource.exception;

import com.hwc.poc.orderservice.application.exception.OrderException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public OrderException handleException(HttpServletRequest request, Exception e) {
        LOGGER.error("error: ", e);
        return new OrderException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
