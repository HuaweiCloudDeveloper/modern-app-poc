package com.hwc.poc.inventoryservice.application.utils;

import io.micrometer.common.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultTemplate<T> {

    private static final String SUCCESS_CODE = "0";
    private static final String SUCCESS_MSG = "Operation succeeded.";

    private static final String FAILED_CODE = "1";
    private static final String FAILED_MSG = "Operation Failed.";

    private String code;
    private String msg;
    private List<T> data;

    public  ResultTemplate(String code, String msg, List<T> data){
        this.code = code;
        this.msg = msg;
        setMultiData(data);
    }

    public  ResultTemplate(String code, String msg, T data){
        this.code = code;
        this.msg = msg;
        setSingleData(data);
    }


    public  ResultTemplate(Boolean isSuccess, List<T> data){
        setResult(isSuccess);
        setMultiData(data);
    }

    public  ResultTemplate(Boolean isSuccess, T data){
        setResult(isSuccess);
        setSingleData(data);
    }

    public ResultTemplate(Boolean isSuccess){

        setResult(isSuccess);
        this.data = null;
    }

    public boolean isSuccess(){
        String curCode = this.code;
        return !StringUtils.isEmpty(curCode) && curCode.equals(SUCCESS_CODE);
    }

    private void setResult(Boolean isSuccess) {
        if(isSuccess){
            this.code = SUCCESS_CODE;
            this.msg = SUCCESS_MSG;
        }else{
            this.code = FAILED_CODE;
            this.msg = FAILED_MSG;
        }
    }

    private void setSingleData(T data) {
        if(data != null){
            this.data = new ArrayList<T>();
            this.data.add(data);
        }
    }

    private void setMultiData(List<T> data) {
        if(data != null){
            this.data = new ArrayList<>(data);
        }
    }
}
