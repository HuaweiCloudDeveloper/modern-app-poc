package com.hwc.poc.inventoryservice.application.exception;


import com.hwc.poc.inventoryservice.resource.exception.ErrorCode;

public class InventoryException extends RuntimeException {
    public InventoryException(ErrorCode internalServerError) {
    }
}
