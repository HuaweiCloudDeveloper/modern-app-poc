package com.hwc.poc.inventoryservice.resource.exception;

public enum ErrorCode {
    /**
     * 服务内部错误
     */
    INTERNAL_SERVER_ERROR(1000, "internal server error"),

    /**
     * 参数错误
     */
    PARAMETER_ERROR(1001, "parameter error");


    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
