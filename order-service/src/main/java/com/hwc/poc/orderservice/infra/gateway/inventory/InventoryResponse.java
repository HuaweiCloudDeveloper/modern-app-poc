package com.hwc.poc.orderservice.infra.gateway.inventory;

import io.micrometer.common.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class InventoryResponse {

    private static final String SUCCESS_CODE = "0";
    private static final String SUCCESS_MSG = "Operation succeeded.";

    private static final String FAILED_CODE = "1";
    private static final String FAILED_MSG = "Operation Failed.";

    private String code;
    private String msg;
    private List<Integer> data;

    public InventoryResponse(String code, String msg, List<Integer> data){
        this.code = code;
        this.msg = msg;
        setMultiData(data);
    }

    public InventoryResponse(String code, String msg, Integer data){
        this.code = code;
        this.msg = msg;
        setSingleData(data);
    }


    public InventoryResponse(Boolean isSuccess, List<Integer> data){
        setResult(isSuccess);
        setMultiData(data);
    }

    public InventoryResponse(Boolean isSuccess, Integer data){
        setResult(isSuccess);
        setSingleData(data);
    }

    public InventoryResponse(Boolean isSuccess){

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

    private void setSingleData(Integer data) {
        if(data != null){
            this.data = new ArrayList<Integer>();
            this.data.add(data);
        }
    }

    private void setMultiData(List<Integer> data) {
        if(data != null){
            this.data = new ArrayList<>(data);
        }
    }
}
