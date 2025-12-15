package com.hwc.poc.orderservice.application.exception;


import com.hwc.poc.orderservice.resource.exception.ErrorCode;

public class OrderException extends RuntimeException {
    public OrderException(ErrorCode internalServerError) {
    }
}
